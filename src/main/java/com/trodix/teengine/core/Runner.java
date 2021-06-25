package com.trodix.teengine.core;

import java.io.Serializable;
import java.util.Map;

import com.trodix.teengine.core.config.ProcessorConfiguration;
import com.trodix.teengine.core.interfaces.Processor;

public class Runner {

    private ProcessorConfiguration config;

    public Runner(ProcessorConfiguration config) {
        this.config = config;
    }

    public String process(String template, Map<String, Serializable> keyValuesPair) {
        String result = template;
        for (Processor p : this.config.getRegisteredProcessors()) {
            result = p.process(result, keyValuesPair);
        }
        return result;
    }
    
}
