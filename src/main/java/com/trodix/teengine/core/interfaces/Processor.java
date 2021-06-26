package com.trodix.teengine.core.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public interface Processor {

    public abstract String process(String rawTemplate, Map<String, Serializable> keyValuesPair);

    public List<String> getVars(String rawTemplate);

    public String replaceValue(String rawTemplate, String key, String value);

    public String removeDelimiter(String input);

    public Pattern getTemplateList();

    public abstract String getStartDelimiter();

    public abstract String getEndDelimiter();

}
