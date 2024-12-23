package ru.tavern.menu.dish.meal;

import ru.tavern.menu.dish.Dish;

public abstract class Meal extends Dish {

    public Meal(String name, float price, String type) {
        super(name, price, type);
    }
}
