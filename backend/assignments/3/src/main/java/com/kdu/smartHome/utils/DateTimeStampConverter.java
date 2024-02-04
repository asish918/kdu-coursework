package com.kdu.smartHome.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeStampConverter {
    public static String convertTimestampFormat(String originalTimestamp) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        LocalDateTime dateTime = LocalDateTime.parse(originalTimestamp, inputFormatter);
        return dateTime.format(outputFormatter);
    }
}
