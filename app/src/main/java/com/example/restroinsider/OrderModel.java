package com.example.restroinsider;

public class OrderModel {
    private String pos;
    private Object items,tableNumber;

    public OrderModel(String pos, Object items, Object tableNumber) {
        this.pos = pos;
        this.items = items;
        this.tableNumber = tableNumber;
    }

    public OrderModel() {
    }

    public String getPos() {
        return pos;
    }

    public Object getItems() {
        return items;
    }

    public Object getTableNumber() {
        return tableNumber;
    }
}
