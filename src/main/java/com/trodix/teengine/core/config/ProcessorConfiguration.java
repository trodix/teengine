package com.trodix.teengine.core.config;

import java.util.ArrayList;
import java.util.List;

import com.trodix.teengine.core.functions.DateProcessor;
import com.trodix.teengine.core.interfaces.Processor;
import com.trodix.teengine.core.processor.PlaceholderProcessor;

public class ProcessorConfiguration {

    private List<Processor> registeredProcessors;

    public ProcessorConfiguration() {
        this.registeredProcessors = new ArrayList<>();
        this.init();
        this.configure();
    }

    private void init() {
        this.registeredProcessors.add(new PlaceholderProcessor());
    }

    private void configure() {
        this.registeredProcessors.add(new DateProcessor());
    }

    public List<Processor> addRegisteredProcessors(Processor processor) {
        this.registeredProcessors.add(processor);

        return this.getRegisteredProcessors();
    }

    public List<Processor> getRegisteredProcessors() {
        return this.registeredProcessors;
    }

}
