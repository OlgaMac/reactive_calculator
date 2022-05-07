package com.example.reactive_calculator.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UnorderedResultDTO extends FunctionResult{
    public int functionNumber;

    @Override
    public String toString() {
        return String.format("%d, %d, %s, %d ns%n", iterationNumber, functionNumber, functionResult, calculationTime);
    }

    public UnorderedResultDTO(Long iterationNumber, int functionNumber, FunctionResult functionResult) {
        this.iterationNumber = iterationNumber;
        this.functionNumber = functionNumber;
        this.functionResult = functionResult.functionResult;
        this.calculationTime = functionResult.calculationTime;
    }
}
