package com.trodix.teengine.processor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

@Service
public class DateProcessor extends AbstractArgsProcessor {

    @Override
    public String process(String rawTemplate, Map<String, Serializable> keyValueAssoc) {
        String processedTemplate = rawTemplate;

        for (Entry<String, Serializable> assocEntry : keyValueAssoc.entrySet()) {
            for (Entry<String, List<String>> argEntry : this.getValueArgsPair(rawTemplate).entrySet()) {
                // arg0: date format
                String dateFormat = argEntry.getValue().get(0);
                SimpleDateFormat formater = new SimpleDateFormat(dateFormat);

                try {
                    Date value = formater.parse(assocEntry.getValue().toString());
                    processedTemplate = this.replaceValue(processedTemplate, assocEntry.getKey(), value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return processedTemplate;
    }

    @Override
    public String getStartDelimiter() {
        return "@date(";
    }

    @Override
    public String getEndDelimiter() {
        return ")";
    }

    @Override
    public String getArgsDelimiter() {
        return ",";
    }

}
