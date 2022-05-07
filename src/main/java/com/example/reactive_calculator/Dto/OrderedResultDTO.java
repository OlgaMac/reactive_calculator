package com.example.reactive_calculator.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderedResultDTO {
    public Long iterationNumber;
    public String functionResult1;
    public long calculationTime1;
    public long calculatedAhead1;

    public String functionResult2;
    public long calculationTime2;
    public long calculatedAhead2;

    @Override
    public String toString() {
        return String.format("%d, %s, %d ns, %d, %s, %d ns, %d%n",
              iterationNumber, functionResult1, calculationTime1, calculatedAhead1,
              functionResult2, calculationTime2, calculatedAhead2);
    }

    public OrderedResultDTO(FunctionResult function1, long calculatedAhead1, FunctionResult function2, long calculatedAhead2) {
        this.iterationNumber = function1.iterationNumber;
        this.functionResult1 = function1.functionResult;
        this.calculationTime1 = function1.calculationTime;
        this.calculatedAhead1 = calculatedAhead1;
        this.functionResult2 = function2.functionResult;
        this.calculationTime2 = function2.calculationTime;
        this.calculatedAhead2 = calculatedAhead2;
    }
}
