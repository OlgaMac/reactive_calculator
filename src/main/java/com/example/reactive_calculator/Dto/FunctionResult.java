package com.example.reactive_calculator.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionResult {
    public Long iterationNumber;
    public String functionResult;
    public long calculationTime;
}
