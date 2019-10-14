package com.herokuapp.schoolmvc.model;

public class Item {
    Long itemId;
    String name;

    public Item() {
        
    }

    public Item(Long itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}