package com.example.reactive_calculator.controller;

import com.example.reactive_calculator.Dto.InputDTO;
import com.example.reactive_calculator.service.CalculationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class CalculatorController {
  final private CalculationService calculationService;

  @ApiOperation(value = "count code")
  @ApiResponses(
        value = {@ApiResponse(code = 200, message = "ok", response = Integer.class)})
  @PostMapping(value = "/count")
  @ResponseStatus(HttpStatus.OK)
  public Flux<String> count(@RequestBody InputDTO input) {
    if (input.getIsAligned() > 0) {
      return calculationService.ordered(input);
    } else {
      return calculationService.unordered(input);
    }
  }
}
