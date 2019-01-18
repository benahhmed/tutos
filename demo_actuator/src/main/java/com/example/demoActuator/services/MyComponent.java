package com.example.demoActuator.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    private final Counter myCounter;

    public MyComponent(MeterRegistry registry) {
        myCounter = Counter
                .builder("mycustomcounter")
                .description("this is my custom counter")
                .register(registry);
    }

    public void countedCall() {
     myCounter.increment();
    }
}