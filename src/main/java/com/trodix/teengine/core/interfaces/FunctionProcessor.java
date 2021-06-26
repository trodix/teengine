package com.trodix.teengine.core.interfaces;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public interface FunctionProcessor extends Processor {

    /**
     * Get the args delimiter between the key dans the args for a processor function
     * 
     * @return The args delimiter
     */
    public abstract String getArgsDelimiter();

    /**
     * Get a pattern matching a key to search in a raw template
     * 
     * @param key The key to search in the template
     * @return The pattern for searching a specific key
     */
    public Pattern getTemplateByKey(String key);

    /**
     * Extract the values with their assiociated args from a template
     * 
     * @param rawTemplate The template to extract the values from
     * @return A list of value / args pair
     */
    public Map<String, List<String>> getValueArgsPair(String rawTemplate);

}
