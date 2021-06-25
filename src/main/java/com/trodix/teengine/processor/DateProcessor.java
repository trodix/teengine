package com.trodix.teengine.processor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateProcessor extends AbstractFunctionProcessor {

    @Override
    public String process(String rawTemplate, Map<String, Serializable> keyValueAssoc) {
        String processedTemplate = rawTemplate;

        for (Entry<String, Serializable> assocEntry : keyValueAssoc.entrySet()) {
            for (Entry<String, List<String>> argEntry : this.getValueArgsPair(rawTemplate).entrySet()) {
                if (argEntry.getKey().equals(assocEntry.getKey())) {
                    // arg0: date format
                    String dateFormat = argEntry.getValue().get(0);
                    DateTimeFormatter formater = DateTimeFormatter.ofPattern(dateFormat);

                    try {
                        LocalDate value = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(assocEntry.getValue().toString()));
                        processedTemplate = this.replaceValue(processedTemplate, assocEntry.getKey(), formater.format(value));
                    } catch (DateTimeParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return processedTemplate;
    }

    @Autowired
    public void setAbstractProcessor(AbstractProcessor coreProcessor) {
        this.coreProcessor = coreProcessor;
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
