package com.example.objectmapping.models.dto;

import java.util.List;

public class SoldProductCollectionDto {

    private int count;

    private List<SoldProductDto> products;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SoldProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<SoldProductDto> products) {
        this.products = products;
    }
}
