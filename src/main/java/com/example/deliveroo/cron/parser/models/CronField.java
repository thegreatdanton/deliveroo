package com.example.deliveroo.cron.parser.models;

import com.example.deliveroo.cron.parser.exceptions.InvalidCronFieldException;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Cron field is used to store actual values, field type and input text
 */
@Getter
@Setter
public class CronField {
    private final String text;
    private FieldType type;
    private Set<Integer> values;

    public CronField(String text, FieldType type) throws InvalidCronFieldException {
        this.text = text;
        this.type = type;
        values = new TreeSet<>();

        parseFixedValues();
        parseRangeValues();
        parseIntervals();

        if(values.isEmpty()){
            values.add(parseNumber(text));
        }
    }

    private Integer parseNumber(String text) throws InvalidCronFieldException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new InvalidCronFieldException("Invalid number '" + text
                    + "' in field " + type + ": " + exception.getMessage());
        }
    }

    private void parseIntervals() throws InvalidCronFieldException {
        if(text.startsWith("*")){
            int interval = 1;
            String[] intervals = text.split("/");
            if(intervals.length > 2){
                throw new InvalidCronFieldException("Number " + text + " for " + type + "has too many intervals");
            }

            if(intervals.length == 2){
                interval = parseNumber(intervals[1]);
            }
            populateValues(type.getStart(), type.getEnd(), interval);
        }
    }

    private void populateValues(int start, int end, int interval) throws InvalidCronFieldException {
        if(interval == 0){
            throw new InvalidCronFieldException("Number " + text + " for " + type + " interval is 0");
        }

        if (end < start) {
            throw new InvalidCronFieldException("Number " + text + " for " + type + " ends before it starts");
        }
        if (start < type.getStart() || end > type.getEnd()) {
            throw new InvalidCronFieldException("Number " + text + " for " + type
                    + " is outside valid range (" + type.getStart() + "-" + type.getEnd() + ")");
        }
        for (int i = start; i <= end; i = i + interval) {
            values.add(i);
        }

    }

    private void parseRangeValues() throws InvalidCronFieldException {
        String[] range = text.split("-");
        if(range.length == 2){
            int start = parseNumber(range[0]);
            int end = parseNumber(range[1]);
            populateValues(start, end, 1);
        }
    }

    private void parseFixedValues() throws InvalidCronFieldException {
        String[] fixedDates = text.split(",");
        if(fixedDates.length > 1){
            for(String date: fixedDates){
                int num = parseNumber(date);
                // pass value to populate() to validate min max ranges
                populateValues(num, num, 1);
            }
        }
    }

    public String toString() {
        return values.stream().map(Object::toString).collect(Collectors.joining(" "));
    }
}
