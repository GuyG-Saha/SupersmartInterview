package com.example.supersmart.entity;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import java.util.List;

@Component
@Data
public class Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);
    private List<Item> items;
    @Autowired
    private List<ValidationProcess> processes;
    private int countSuccess;

    public Validator(List<Item> items) {
        this.items = items;
    }

    public boolean validate() {
        countSuccess = 0;
        for (ValidationProcess process : processes) {
            if (process.execute(items)) {
                LOGGER.info("Success for " + process.getClass());
                countSuccess++;
            }
        }
        return countSuccess >= 2;
    }
}
