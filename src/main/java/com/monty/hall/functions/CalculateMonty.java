package com.monty.hall.functions;

import com.monty.hall.entities.DoorResult;
import com.monty.hall.entities.MontyResponse;
import com.monty.hall.entities.WinResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class CalculateMonty {
    static Logger logger = LoggerFactory.getLogger(CalculateMonty.class);

    public static final int DOORS=3;

    private static Random random = new Random();

    private static int pickADoor(){
        return random.nextInt(DOORS);
    }

    private static int pickADoor(int doorA, int doorB){
        int result;
        do{
            result = pickADoor();
        }
        while (result == doorA || result == doorB);
        return result;
    }

    private static DoorResult saveResult(DoorResult result,DoorResult current){
        return result.builder()
                .prizeDoor(result.getPrizeDoor()+current.getPrizeDoor())
                .chosenDoor(result.getChosenDoor()+current.getChosenDoor())
                .montyDoor(result.getMontyDoor()+current.getMontyDoor())
                .makeASwitch(result.getMakeASwitch()+current.getMakeASwitch())
                .build();
    }

    private static Double getPercentage(double result, double total) {
        BigDecimal bd = new BigDecimal(result * 100 / total);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public static MontyResponse getMontyResponse(int games){
        //local variables
        int switched = 0,notSwitched = 0,remaining = games;

        DoorResult result=DoorResult.builder().build();

        //loop the loop
        while(remaining!=0) {
            int prizeDoor = pickADoor();
            int chosenDoor = pickADoor();
            int montyDoor = pickADoor(chosenDoor, prizeDoor);
            int makeASwitch = pickADoor(chosenDoor, montyDoor);

            //it might be nice to save total results
            result=saveResult(result,DoorResult
                    .builder()
                    .prizeDoor(prizeDoor)
                    .chosenDoor(chosenDoor)
                    .montyDoor(montyDoor)
                    .makeASwitch(makeASwitch)
                    .build());

            if (chosenDoor == prizeDoor) {
                notSwitched++;
            }
            if (makeASwitch == prizeDoor) {
                switched++;
            }
            remaining--;
        }

        DecimalFormat df = new DecimalFormat("0.00%");

        logger.info("not switched: "+ df.format(getPercentage(notSwitched,games)));
        logger.info("switched: "+df.format(getPercentage(switched,games)));

        //The response object
        MontyResponse response=MontyResponse
                .builder()
                .games(games)
                .doors(DOORS)
                .wins(WinResult.builder()
                        .switched(switched)
                        //always lovely with a percentage
                        .switchedPercent(getPercentage(switched,games))
                        .notSwitched(notSwitched)
                        .notSwitchedPercent(getPercentage(notSwitched,games))
                        .build())
                .statistics(result)
                .build();

        return response;
    }
}
