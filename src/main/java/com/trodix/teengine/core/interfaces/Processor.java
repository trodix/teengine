package com.trodix.teengine.core.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public interface Processor {

    /**
     * Process a raw template to populate placeholders with it's value. Each
     * processor replace it's own delimiters.
     * 
     * @param rawTemplate   The raw template to inject values into
     * @param keyValuesPair The key / value pair to inject
     * @return The new template with values.
     */
    public abstract String process(String rawTemplate, Map<String, Serializable> keyValuesPair);

    /**
     * Extract the list of keys found inside a delimiter
     * 
     * @param rawTemplate The raw template to extract keys from
     * @return The list of keys matching the delimiter
     */
    public List<String> getVars(String rawTemplate);

    /**
     * Replace a key by it's associated value
     * 
     * @param rawTemplate The raw template to replace the value into
     * @param key         The key to replace
     * @param value       The value to inject
     * @return The new template with the value
     */
    public String replaceValue(String rawTemplate, String key, String value);

    /**
     * Remove the delimiter inside a matching group of a raw template
     * 
     * @param input The matching group
     * @return The matching group without the delimiter
     */
    public String removeDelimiter(String input);

    /**
     * Get the regex pattern matching the delimiters into a raw template
     * 
     * @return The regex
     */
    public Pattern getTemplateList();

    /**
     * Get the start delimiter of a processor
     * 
     * @return The start delimiter
     */
    public abstract String getStartDelimiter();

    /**
     * Get the end delimiter of a processor
     * 
     * @return The end delimiter
     */
    public abstract String getEndDelimiter();

}
