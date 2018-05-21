package com.akhil.projects.statistics.controller;

import java.util.Date;

import com.akhil.projects.statistics.controller.dto.StatisticsDto;
import com.akhil.projects.statistics.controller.dto.TransactionDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {

    TransactionDto transaction1 = new TransactionDto();
    TransactionDto transaction2 = new TransactionDto();
    TransactionDto transaction3 = new TransactionDto();

    @Autowired
    MainController mainController;

    @Before
    public void init() {
        transaction1.setAmount(12.3);
        transaction1.setTimestamp(1526800800000l);

        transaction2.setAmount(12.5);
        transaction2.setTimestamp(new Date().getTime());

        transaction3.setAmount(12.7);
        transaction3.setTimestamp(new Date().getTime());
    }

    @Test
    public void saveTransaction() {
        ResponseEntity responseEntity = mainController.saveTransaction(transaction1);
        Assert.assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    public void saveTransaction1() {
        ResponseEntity responseEntity = mainController.saveTransaction(transaction2);
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getStatistics() {
        mainController.saveTransaction(transaction1);
        mainController.saveTransaction(transaction2);
        mainController.saveTransaction(transaction3);
        StatisticsDto statisticsDto = mainController.getStatistics();
        Assert.assertEquals(2, statisticsDto.getCount());
        Assert.assertEquals(12.7, statisticsDto.getMax().doubleValue(), 0);
        Assert.assertEquals(12.5, statisticsDto.getMin().doubleValue(), 0);
        Assert.assertEquals(25.2, statisticsDto.getSum().doubleValue(), 0);
        Assert.assertEquals(12.6, statisticsDto.getAvg().doubleValue(), 0);
    }
}