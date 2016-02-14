package com.ubs.opsit.interviews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis Pavlenko on 14.02.2016.
 */
public class BerlinClock implements TimeConverter {

    @Override
    public String convertTime(String aTime) {
        if (!aTime.matches(ConfigClock.TIME_FORMAT)) {
            throw new IllegalArgumentException("Time must be in the format HH:MM:SS, but we have - " + aTime);
        }

        List<Integer> parts = new ArrayList<>();
        for (String part : aTime.split(ConfigClock.SEPARATOR_SIGN)) {
            parts.add(Integer.parseInt(part));
        }

        return new StringBuilder().
                append(getSeconds(parts.get(2))).append(ConfigClock.NEW_LINE).
                append(getTopRowOfHours(parts.get(0))).append(ConfigClock.NEW_LINE).
                append(getBottomRowOfHours(parts.get(0))).append(ConfigClock.NEW_LINE).
                append(getTopRowOfMinutes(parts.get(1))).append(ConfigClock.NEW_LINE).
                append(getBottomRowOfMinutes(parts.get(1))).
                toString();
    }

    protected String getSeconds(int value) {
        if (value % 2 == 0)
            return ConfigClock.ON_SIGN;
        else
            return ConfigClock.OFF_SIGN;
    }

    protected String getTopRowOfHours(int value) {
        return getOnOff(
                ConfigClock.COUNT_OF_TOP_HOURS,
                getTopNumberOfOnSigns(value));
    }

    protected String getBottomRowOfHours(int value) {
        return getOnOff(
                ConfigClock.COUNT_OF_BOTTOM_HOURS,
                value % 5);
    }

    protected String getTopRowOfMinutes(int value) {
        return getOnOff(
                ConfigClock.COUNT_OF_TOP_MINUTES,
                getTopNumberOfOnSigns(value),
                ConfigClock.ON_SIGN).replaceAll(
                    ConfigClock.TOP_ROW_OF_MINUTES_WITHOUT_QUARTERS,
                    ConfigClock.TOP_ROW_OF_MINUTES_WITH_QUARTERS);
    }

    protected String getBottomRowOfMinutes(int value) {
        return getOnOff(
                ConfigClock.COUNT_OF_BOTTOM_MINUTES,
                value % 5,
                ConfigClock.ON_SIGN);
    }

    private String getOnOff(int lampsInSummary, int lampsIsOnCount) {
        return getOnOff(
                lampsInSummary,
                lampsIsOnCount,
                ConfigClock.QUARTER_SIGN);
    }

    private String getOnOff(int lampsInSummary, int lampsIsOnCount, String notOffSign) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < lampsIsOnCount; i++) {
            out.append(notOffSign);
        }

        for (int i = 0; i < (lampsInSummary - lampsIsOnCount); i++) {
            out.append(ConfigClock.OFF_SIGN);
        }
        return out.toString();
    }

    private int getTopNumberOfOnSigns(int value) {
        return (value - (value % 5)) / 5;
    }
}