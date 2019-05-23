package com.monty.hall.rest;


import com.monty.hall.functions.CalculateMonty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/run")
@Validated
public class MontyRest {
    Logger logger = LoggerFactory.getLogger(this.getClass());



    @RequestMapping(path = "/monty",method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> runMonty(@RequestParam(value = "games",defaultValue = "${monty.games}") @Valid @Positive int games){
        return new ResponseEntity(CalculateMonty.getMontyResponse(games),HttpStatus.ACCEPTED);
    }
}

