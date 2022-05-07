package com.example.reactive_calculator.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputDTO {
    private String code1;
    private String code2;
    private int isAligned;
    private int times;
}
