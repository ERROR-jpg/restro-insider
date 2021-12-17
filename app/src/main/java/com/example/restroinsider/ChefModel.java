package com.example.restroinsider;

public class ChefModel {
    String tableNumber, note;
    String pos;

    public ChefModel() {
    }

    public ChefModel(String tableNumber, String note, String pos) {
        this.tableNumber = tableNumber;
        this.note = note;
        this.pos = pos;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
