package com.example.deliveroo.cron.parser.exceptions;

public class InvalidCronFieldException extends Throwable{
    public InvalidCronFieldException(String message) {
        super(message);
    }
}
