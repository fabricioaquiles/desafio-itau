package com.fabricioaquiles.desafioitau.web.response;

import lombok.Getter;

import java.util.DoubleSummaryStatistics;

@Getter
public class StatisticsResponse {

    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public StatisticsResponse(DoubleSummaryStatistics statistics) {
        this.count = statistics.getCount();
        this.sum = statistics.getSum();
        this.avg = statistics.getAverage();
        this.min = statistics.getCount() > 0 ? statistics.getMin() : 0.0;
        this.max = statistics.getCount() > 0 ? statistics.getMax() : 0.0;
    }
}
