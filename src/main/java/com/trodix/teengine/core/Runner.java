package com.trodix.teengine.core;

import java.io.Serializable;
import java.util.Map;

import com.trodix.teengine.core.config.ProcessorConfiguration;
import com.trodix.teengine.core.interfaces.Processor;

public class Runner {

    private ProcessorConfiguration config;

    /**
     * Constructor
     * 
     * @param config The configuration for the runner
     */
    public Runner(ProcessorConfiguration config) {
        this.config = config;
    }

    /**
     * Process the raw template with all registered processors
     * @param template The raw template to process
     * @param keyValuesPair The key / value pair to inject info the template
     * @return The processed result 
     */
    public String process(String template, Map<String, Serializable> keyValuesPair) {
        String result = template;
        for (Processor p : this.config.getRegisteredProcessors()) {
            result = p.process(result, keyValuesPair);
        }
        return result;
    }

}
