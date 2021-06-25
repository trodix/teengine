package com.trodix.teengine.processor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trodix.teengine.interfaces.Processor;

public abstract class AbstractProcessor implements Processor {

    public abstract String getStartDelimiter();
    public abstract String getEndDelimiter();

    public Pattern getDelimiter() {
        return Pattern.compile(MessageFormat.format("{0}\\s*\\w+\\s*{1}", this.getStartDelimiter(), this.getEndDelimiter()));
    }

    public List<String> getVars(String rawTemplate) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = getDelimiter().matcher(rawTemplate);

        while (matcher.find()) {
            matches.add(matcher.group().replaceFirst("\\" + this.getStartDelimiter(), "").replaceFirst("\\" + this.getEndDelimiter(), ""));
        }

        return matches;
    }

    public List<String> getPlaceholders(String rawTemplate) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = getDelimiter().matcher(rawTemplate);

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches;
    }

    public String replaceValue(String rawTemplate, String key, String value) {
        String patternToReplace = this.getStartDelimiter() + key + this.getEndDelimiter();
        return rawTemplate.replace(patternToReplace, value);
    }

}
