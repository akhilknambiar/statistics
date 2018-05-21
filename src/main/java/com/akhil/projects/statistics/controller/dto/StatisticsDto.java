package com.akhil.projects.statistics.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticsDto {
    Double sum = new Double(0), avg, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
    long count = 0;
}
