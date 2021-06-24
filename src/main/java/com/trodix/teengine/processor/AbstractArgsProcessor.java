package com.trodix.teengine.processor;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trodix.teengine.interfaces.Processor;

public abstract class AbstractArgsProcessor implements Processor {

    public abstract String getStartDelimiter();
    public abstract String getEndDelimiter();
    public abstract String getArgsDelimiter();

    public Pattern getDelimiter() {
        // return Pattern.compile("\\{\\w+\\}g");
        return Pattern.compile(MessageFormat.format("{0}w+{1}g", this.getStartDelimiter(), this.getEndDelimiter()));
    }

    public Map<String, List<String>> getValueArgsPair(String rawTemplate) {
        Map<String, List<String>> argsMap = new HashMap<>();

        for (String match : this.getVars(rawTemplate)) {
            List<String> currentArgs = Arrays.asList(match.split(this.getArgsDelimiter()));

            String value = currentArgs.get(0);
            currentArgs.remove(0);
            List<String> args = currentArgs;

            argsMap.put(value, args);
        }

        return argsMap;
    }

    public List<String> getVars(String rawTemplate) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = getDelimiter().matcher(rawTemplate);

        while (matcher.find()) {
            matches.add(matcher.group().replaceFirst(this.getStartDelimiter(), "").replaceFirst(this.getEndDelimiter(), ""));
        }

        return matches;
    }

    public String replaceValue(String rawTemplate, String key, String value) {
        String patternToReplace = this.getStartDelimiter() + key + this.getEndDelimiter();
        return rawTemplate.replace(patternToReplace, value);
    }

}
