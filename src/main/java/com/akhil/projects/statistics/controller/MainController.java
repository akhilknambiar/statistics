package com.akhil.projects.statistics.controller;

import java.util.Date;

import com.akhil.projects.statistics.controller.dto.StatisticsDto;
import com.akhil.projects.statistics.controller.dto.TransactionDto;
import com.akhil.projects.statistics.data.DataStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Value("${duration}")
    long duration;

    @PostMapping("/transactions")
    public ResponseEntity saveTransaction(@RequestBody TransactionDto transaction) {

        long startingTimestamp = new Date().getTime() - duration;

        if (transaction.getTimestamp() < startingTimestamp) {
            return ResponseEntity.status(204).build();
        }

        DataStore.getInstance().push(transaction.getTimestamp(), transaction.getAmount());
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/statistics")
    public StatisticsDto getStatistics() {
        long startingTimestamp = new Date().getTime() - duration;
        return DataStore.getInstance().getStatistics(startingTimestamp);
    }

}
