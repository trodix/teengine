package com.trodix.teengine.core.config;

import java.util.ArrayList;
import java.util.List;

import com.trodix.teengine.core.functions.DateProcessor;
import com.trodix.teengine.core.interfaces.Processor;
import com.trodix.teengine.core.processor.PlaceholderProcessor;

public class ProcessorConfiguration {

    private List<Processor> registeredProcessors;

    /**
     * Constructor
     */
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

    /**
     * Register a new processor to use in the runner
     * 
     * @param processor The new processor to register
     * @return All registered processors
     */
    public List<Processor> addRegisteredProcessors(Processor processor) {
        this.registeredProcessors.add(processor);

        return this.getRegisteredProcessors();
    }

    /**
     * Get all registered processors for this configuration
     * 
     * @return All registered processors
     */
    public List<Processor> getRegisteredProcessors() {
        return this.registeredProcessors;
    }

}
