package org.example;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeConverter {
    public static void main(String[] args){
        //Get the time zone for New York
        ZoneId newYork = ZoneId.of("America/New_York");

        // Get the current date and time in New York
        ZonedDateTime newYorkTime = ZonedDateTime.now(newYork);

        // Convert the time to Tokyo time
        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoTime = newYorkTime.withZoneSameInstant(tokyo);

        // Print the converted time
        System.out.println("New York time: " + newYorkTime);
        System.out.println("Tokyo time: " + tokyoTime);
    }
}
