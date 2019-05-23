package com.monty.hall.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MontyResponse {
    int games;
    int doors;
    WinResult wins;
    DoorResult statistics;
}
