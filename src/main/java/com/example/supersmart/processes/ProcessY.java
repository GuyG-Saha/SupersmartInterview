package com.example.supersmart.processes;

import com.example.supersmart.entity.Item;
import com.example.supersmart.entity.ItemType;
import com.example.supersmart.entity.ValidationProcess;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
@Component
public class ProcessY implements ValidationProcess {
    @Override
    public boolean execute(List<Item> items) {
        return items
                .stream()
                .filter(i -> Objects.equals(i.getType(), ItemType.WEIGHTED))
                .mapToDouble(Item::getWeight)
                .sum() < 10;
    }
}
