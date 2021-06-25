package com.trodix.teengine.core.processor;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

public class PlaceholderProcessor extends AbstractProcessor {

    @Override
    public String process(String rawTemplate, Map<String, Serializable> keyValueAssoc) {
        String processedTemplate = rawTemplate;

        for (Entry<String, Serializable> assocEntry : keyValueAssoc.entrySet()) {
            processedTemplate = this.replaceValue(processedTemplate, assocEntry.getKey(), assocEntry.getValue().toString());
        }

        return processedTemplate;
    }

    @Override
    public String getStartDelimiter() {
        return "{";
    }

    @Override
    public String getEndDelimiter() {
        return "}";
    }
    
}
