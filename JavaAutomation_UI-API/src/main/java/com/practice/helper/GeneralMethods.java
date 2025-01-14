package com.practice.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneralMethods {
    public static String getCurrentTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        System.out.println(formattedDateTime);
        return formattedDateTime;
    }
}
