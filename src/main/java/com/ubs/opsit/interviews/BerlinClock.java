package com.ubs.opsit.interviews;

/**
 * Created by Denis Pavlenko on 14.02.2016.
 */
public class BerlinClock implements TimeConverter {

    @Override
    public String convertTime(String aTime) {
        if (!aTime.matches(ConfigClock.TIME_FORMAT)) {
            throw new IllegalArgumentException("Time must be in the format HH:MM:SS");
        }

        return null;
    }
}