package ru.tavern.menu.dish;

public abstract class Dish {

    // Объявление полей класса
    private String name;
    private float price;
    private String type;

    // Конструктор
    public Dish(String name, float price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    // Свойства класса
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
