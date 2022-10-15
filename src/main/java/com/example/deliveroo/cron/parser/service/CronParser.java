package com.example.deliveroo.cron.parser.service;

import com.example.deliveroo.cron.parser.exceptions.InvalidCronFieldException;
import com.example.deliveroo.cron.parser.models.CronExpression;
import com.example.deliveroo.cron.parser.models.CronField;
import com.example.deliveroo.cron.parser.models.FieldType;

public class CronParser {
    public CronParser() {
    }

    public CronExpression parseExpression(String inputExpression) throws InvalidCronFieldException{
         CronField minutes;
         CronField hours;
         CronField dayOfMonth;
         CronField month;
         CronField dayOfWeek;
         String command;

        String[] cronMembers = inputExpression.split("\\s+");
        if (cronMembers.length != 6) {
            throw new InvalidCronFieldException("Expected [minute] [hour] [day of month] [day of week] [month] [command] but got: " + inputExpression);
        }

        minutes = new CronField(cronMembers[0], FieldType.MINUTE);
        hours = new CronField(cronMembers[1], FieldType.HOUR);
        dayOfMonth = new CronField(cronMembers[2], FieldType.DAY_OF_MONTH);
        month = new CronField(cronMembers[3], FieldType.MONTH);
        dayOfWeek = new CronField(cronMembers[4], FieldType.DAY_OF_WEEK);
        command = cronMembers[5];

        return new CronExpression(minutes, hours, dayOfMonth, month, dayOfWeek, command);
    }
}
