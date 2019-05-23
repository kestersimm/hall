package com.monty.hall.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WinResult {
    double notSwitched;
    double notSwitchedPercent;
    double switched;
    double switchedPercent;
}
