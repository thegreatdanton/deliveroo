package com.example.deliveroo.cron.parser.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.String.format;

/**
 *  Model to handle final cron expression
 */
@Getter
@AllArgsConstructor
public class CronExpression {
    public static final String OUTPUT_FORMAT = "%-14s%s%n";
    private CronField minutes;
    private CronField hours;
    private CronField dayOfMonth;
    private CronField month;
    private CronField dayOfWeek;
    private String command;

    public String toString() {
        StringBuilder output = new StringBuilder();
        if(minutes != null){
            output.append(format(OUTPUT_FORMAT, "minute", minutes.toString()));
        }
        if(hours != null){
            output.append(format(OUTPUT_FORMAT, "hour", hours.toString()));
        }
        if(dayOfMonth != null){
            output.append(format(OUTPUT_FORMAT, "day of month", dayOfMonth.toString()));
        }
        if(month != null){
            output.append(format(OUTPUT_FORMAT, "month", month.toString()));
        }
        if(dayOfWeek != null){
            output.append(format(OUTPUT_FORMAT, "day of week", dayOfWeek.toString()));
        }
        if(command != null){
            output.append(format(OUTPUT_FORMAT, "command", command));
        }
        return output.toString();
    }
}
