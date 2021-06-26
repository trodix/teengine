package com.trodix.teengine.core.interfaces;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public interface FunctionProcessor extends Processor {

    public abstract String getArgsDelimiter();

    public Pattern getTemplateByKey(String key);

    public Map<String, List<String>> getValueArgsPair(String rawTemplate);

}
