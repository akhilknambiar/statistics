package com.akhil.projects.statistics.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticInstantTest {

    StatisticInstant statisticInstant;

    @Test
    public void StatisticInstantConstructorTest() {
        statisticInstant = new StatisticInstant(15);
        Assert.assertEquals(15, statisticInstant.getMax(), 0);
        Assert.assertEquals(15, statisticInstant.getMin(), 0);
        Assert.assertEquals(15, statisticInstant.getSum(), 0);
        Assert.assertEquals(1, statisticInstant.getCount());
    }

    @Test
    public void updateStatisticInstant() {
        statisticInstant = new StatisticInstant(15);
        statisticInstant.updateStatisticInstant(12.3);
        Assert.assertEquals(15, statisticInstant.getMax(), 0);
        Assert.assertEquals(12.3, statisticInstant.getMin(), 0);
        Assert.assertEquals(27.3, statisticInstant.getSum(), 0);
        Assert.assertEquals(2, statisticInstant.getCount());
    }
}