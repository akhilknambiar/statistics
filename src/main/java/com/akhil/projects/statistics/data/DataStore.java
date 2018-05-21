package com.akhil.projects.statistics.data;

import java.util.concurrent.ConcurrentHashMap;

import com.akhil.projects.statistics.controller.dto.StatisticsDto;

public class DataStore {

    private ConcurrentHashMap<Long, StatisticInstant> dataset = new ConcurrentHashMap<>();

    private static DataStore singletonInstance = new DataStore();

    public static DataStore getInstance() {
        return singletonInstance;
    }

    private DataStore() {
    }

    /**
     * Compress the data while pushing. Suppose we get n number of transaction
     * in a particular millisecond we just need to store the count sum max and
     * min. Remaining values are not required for calculation of statistic.
     *
     * @param timestamp timestamp
     * @param amount amount
     */
    public synchronized void push(long timestamp, double amount) {
        StatisticInstant statisticInstant = dataset.get(timestamp);
        if (statisticInstant == null) {
            statisticInstant = new StatisticInstant(amount);
        } else {
            statisticInstant.updateStatisticInstant(amount);
        }
        dataset.put(timestamp, statisticInstant);
    }


    public synchronized StatisticsDto getStatistics(long startingTimestamp) {

        StatisticsDto statistics = new StatisticsDto();
        dataset.forEach(
                (timestamp, statisticInstant) -> {

                    if (timestamp < startingTimestamp) {
                        dataset.remove(timestamp); // just remove old data as its not required further
                    } else {
                        statistics.setSum(Double.sum(statistics.getSum(), statisticInstant.getSum()));
                        statistics.setMax(Double.max(statistics.getMax(), statisticInstant.getMax()));
                        statistics.setMin(Double.min(statistics.getMin(), statisticInstant.getMin()));
                        statistics.setCount(statistics.getCount() + statisticInstant.getCount());
                    }

                }
        );

        // if no transaction default the values
        if (statistics.getCount() != 0) {
            statistics.setAvg(statistics.getSum() / statistics.getCount());
        } else {
            statistics.setMax(null);
            statistics.setMin(null);
        }

        return statistics;
    }
}
