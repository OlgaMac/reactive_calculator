package com.example.reactive_calculator.service;

import com.example.reactive_calculator.Dto.OrderedResultDTO;
import com.example.reactive_calculator.Dto.FunctionResult;
import com.example.reactive_calculator.Dto.UnorderedResultDTO;
import com.example.reactive_calculator.Dto.InputDTO;
import com.example.reactive_calculator.config.CalculatorProperties;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CalculationService {

    private final Flux<Long> interval;


    public CalculationService(CalculatorProperties calcProperties) {
        this.interval = Flux.interval(Duration.ofSeconds(calcProperties.getPeriod()));
    }

    public Flux<String> unordered(InputDTO input) {
        int times = input.getTimes();
        Flux<String> firstCode = Flux.from(interval)
                .takeUntil((i) -> i > times - 2)
                .map((i) -> (new UnorderedResultDTO(i, 1, execJS(input.getCode1(), i))).toString());
        Flux<String> secondCode = Flux.from(interval)
                .takeUntil((i) -> i > times - 2)
                .map((i) -> (new UnorderedResultDTO(i, 2, execJS(input.getCode2(), i))).toString());
        return Flux.merge(firstCode, secondCode);
    }

    public Flux<String> ordered(InputDTO input) {
        int times = input.getTimes();
        AtomicInteger fr1Total = new AtomicInteger();
        AtomicInteger fr2Total = new AtomicInteger();
        Flux<FunctionResult> firstCode = Flux.from(interval)
                .takeUntil((i) -> i > times - 2)
                .doOnNext((i) -> fr1Total.getAndIncrement())
                .map((i) -> execJS(input.getCode1(), i));
        Flux<FunctionResult> secondCode = Flux.from(interval)
                .takeUntil((i) -> i > times - 2)
                .doOnNext((i) -> fr2Total.getAndIncrement())
                .map((i) -> execJS(input.getCode2(), i));
        return firstCode.zipWith(secondCode, (fr1, fr2) -> (new OrderedResultDTO(
                fr1, fr1Total.get() - fr2.iterationNumber - 1,
                fr2, fr2Total.get() - fr1.iterationNumber - 1)).toString());
    }

    private FunctionResult execJS(String code, Long iter) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            long t0 = System.nanoTime();
            engine.eval(code);
            Invocable invokable = (Invocable) engine;
            String funcResult = invokable.invokeFunction("operation", iter).toString();
            long t1 = System.nanoTime();
            return new FunctionResult(iter, funcResult, t1 - t0);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
            return new FunctionResult(iter, e.getMessage(), 0);
        }
    }
}
