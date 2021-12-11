package com.example.restroinsider;

public class Model {
    String menuitemname;
    Model(){}
    Model(String menuitemname){
        this.menuitemname = menuitemname;
    }

    public String getMenuitemname() {
        return menuitemname;
    }

    public void setMenuitemname(String menuitemname) {
        this.menuitemname = menuitemname;
    }
}
