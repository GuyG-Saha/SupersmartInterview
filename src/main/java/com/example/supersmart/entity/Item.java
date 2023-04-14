package com.example.supersmart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String identifier;
    private String barcode;
    private double weight;
    private ItemType type;

}
