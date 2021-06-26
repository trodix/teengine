package com.trodix.teengine.core.processor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trodix.teengine.core.interfaces.Processor;

public abstract class AbstractProcessor implements Processor {

    public Pattern getTemplateList() {
        return Pattern.compile(
                MessageFormat.format(".*({0}\\s*\\w+\\s*{1}).*", this.getStartDelimiter(), this.getEndDelimiter()));
    }

    public List<String> getVars(String rawTemplate) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = getTemplateList().matcher(rawTemplate);

        while (matcher.find()) {
            matches.add(this.removeDelimiter(matcher.group(1)));
        }

        return matches;
    }

    public String removeDelimiter(String input) {
        return input.replace(this.getStartDelimiter(), "").replace(this.getEndDelimiter(), "");
    }

    public String replaceValue(String rawTemplate, String key, String value) {
        String patternToReplace = this.getStartDelimiter() + key + this.getEndDelimiter();
        return rawTemplate.replace(patternToReplace, value);
    }

}
