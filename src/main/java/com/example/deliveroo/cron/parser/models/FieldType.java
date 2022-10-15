package com.example.deliveroo.cron.parser.models;

import lombok.Getter;

@Getter
public enum FieldType {
    MINUTE(0, 59),
    HOUR(0, 23),
    DAY_OF_MONTH(1, 31),
    MONTH(1, 12),
    DAY_OF_WEEK(1, 7);

    private final int start;
    private final int end;

    FieldType(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
