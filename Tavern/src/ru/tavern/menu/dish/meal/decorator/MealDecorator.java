package ru.tavern.menu.dish.meal.decorator;

import ru.tavern.menu.dish.meal.Meal;

public abstract class MealDecorator extends Meal  {
    protected Meal meal;

    public MealDecorator(Meal meal) {
        super(meal.getName(), meal.getPrice(), meal.getType());
        this.meal = meal;
    }

}
