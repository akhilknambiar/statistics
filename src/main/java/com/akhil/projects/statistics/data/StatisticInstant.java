package com.akhil.projects.statistics.data;

import lombok.Getter;

/**
 * The StatisticInstant is a class which keep the statistic of that millisecond.
 * This is done to avoid storing huge amount of data for a particular millisecond.
 * So we now have just one data structure for each milli second even if we have
 * received millions of data for a single millisecond
 */
@Getter
public class StatisticInstant {
    private double sum = 0, max, min;
    private long count = 0;

    StatisticInstant(double amount) {
        max = amount;
        min = amount;
        sum = amount;
        count = 1;
    }

    /**
     * when updating only required values gets updated
     *
     * @param amount amount
     */
    void updateStatisticInstant(double amount) {
        count++;
        sum += amount;
        if (amount < min) {
            min = amount;
        } else if (amount > max) {
            max = amount;
        }
    }
}
