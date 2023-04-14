package com.example.supersmart.processes;

import com.example.supersmart.entity.Item;
import com.example.supersmart.entity.ValidationProcess;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProcessX implements ValidationProcess {
    @Override
    public boolean execute(List<Item> items) {
        return items
                .stream()
                .mapToDouble(Item::getWeight)
                .sum() > 40;
    }
}
