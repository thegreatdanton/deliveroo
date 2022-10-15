package com.example.deliveroo.cron.parser;

import com.example.deliveroo.cron.parser.exceptions.InvalidCronFieldException;
import com.example.deliveroo.cron.parser.models.CronExpression;
import com.example.deliveroo.cron.parser.service.CronParser;

import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("Expected [minute] [hour] [day of month] [day of week] [month] [command] but got : " + Arrays.toString(args));
            return;
        }

        try {
            CronParser cronParser = new CronParser();
            CronExpression expr = cronParser.parseExpression(args[0]);
            System.out.println(expr);
        } catch (InvalidCronFieldException invalidCronExpression) {
            System.err.println(invalidCronExpression.getMessage());
        }
    }
}
