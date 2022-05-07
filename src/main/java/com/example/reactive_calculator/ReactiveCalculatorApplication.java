package com.example.reactive_calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReactiveCalculatorApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(ReactiveCalculatorApplication.class, args);
  }

}
