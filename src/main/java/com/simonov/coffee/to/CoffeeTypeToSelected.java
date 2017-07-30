package com.simonov.coffee.to;


public class CoffeeTypeToSelected {
    private int id;
    private  String typeName;
    private  int quantity;
    private  Double price;
    private String selected;

    public CoffeeTypeToSelected(int id, String typeName, int quantity, Double price, String selected) {
        this.id = id;
        this.typeName = typeName;
        this.quantity = quantity;
        this.price = price;
        this.selected = selected;
    }

    public CoffeeTypeToSelected() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

}
