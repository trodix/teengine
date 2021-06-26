package com.trodix.teengine.core.processor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.trodix.teengine.core.interfaces.FunctionProcessor;

public abstract class AbstractFunctionProcessor implements FunctionProcessor {

    protected AbstractProcessor coreProcessor;

    protected AbstractFunctionProcessor() {
        this.coreProcessor = new PlaceholderProcessor();
    }

    public Pattern getTemplateList() {
        String safeRegex = MessageFormat.format(".*(\\{0}\\s*[{1}]+\\s*\\w+\\s*,\\s*[\\w|-]+\\s*[{2}+]\\s*\\{3}).*",
                this.coreProcessor.getStartDelimiter(), this.getStartDelimiter(), this.getEndDelimiter(),
                this.coreProcessor.getEndDelimiter());
        return Pattern.compile(safeRegex);
    }

    public Pattern getTemplateByKey(String key) {
        String safeRegex = MessageFormat.format(".*(\\{0}\\s*[{1}]+\\s*{2}\\s*,\\s*[\\w|-]+\\s*[{3}+]\\s*\\{4}).*",
                this.coreProcessor.getStartDelimiter(), this.getStartDelimiter(), key, this.getEndDelimiter(),
                this.coreProcessor.getEndDelimiter());
        return Pattern.compile(safeRegex);
    }

    public Map<String, List<String>> getValueArgsPair(String rawTemplate) {
        Map<String, List<String>> argsMap = new HashMap<>();

        for (String match : this.getVars(rawTemplate)) {
            List<String> currentArgs = new ArrayList<>();
            currentArgs.addAll(Arrays.asList(match.split(this.getArgsDelimiter())).stream().map(i -> i.trim())
                    .collect(Collectors.toList()));

            String value = currentArgs.get(0);
            currentArgs.remove(0);
            List<String> args = currentArgs;

            argsMap.put(value, args);
        }

        return argsMap;
    }

    public List<String> getVars(String rawTemplate) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = getTemplateList().matcher(rawTemplate);

        while (matcher.find()) {
            matches.add(removeDelimiter(matcher.group(1)));
        }

        return matches;
    }

    public String removeDelimiter(String input) {
        return input.replace(this.coreProcessor.getStartDelimiter(), "").replace(this.getStartDelimiter(), "")
                .replace(this.getEndDelimiter(), "").replace(this.coreProcessor.getEndDelimiter(), "");
    }

    public String replaceValue(String rawTemplate, String key, String value) {
        String result = rawTemplate;
        Matcher matcher = this.getTemplateByKey(key).matcher(rawTemplate);

        while (matcher.find()) {
            String patternToReplace = matcher.group(1);
            result = result.replace(patternToReplace, value);
        }
        return result;
    }

}
