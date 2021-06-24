package com.trodix.teengine.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Processor {

    public abstract String process(String rawTemplate, Map<String, Serializable> keyValuesPair);
    public List<String> getVars(String rawTemplate);
    public String replaceValue(String rawTemplate, String key, String value);
    
}
