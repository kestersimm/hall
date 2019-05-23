package com.monty.hall.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoorResult {
    int prizeDoor;
    int chosenDoor;
    int montyDoor;
    int makeASwitch;
}
