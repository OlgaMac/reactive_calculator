package com.example.reactive_calculator.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Data
@ConfigurationPropertiesScan("calculations")
@Component
public class CalculatorProperties {
    int period;
}
