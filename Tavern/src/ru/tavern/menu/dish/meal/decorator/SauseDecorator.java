package ru.tavern.menu.dish.meal.decorator;

import ru.tavern.menu.dish.meal.Meal;

public class SauseDecorator extends MealDecorator {

    public SauseDecorator(Meal meal){
        super(meal);
    }

    @Override
    public String getName() {
        return meal.getName() + " + соус";
    }

    @Override
    public float getPrice() {
        return meal.getPrice() + 90;
    }

    @Override
    public String getType() {
        if (meal.getType().equals("Блюдо с рыбой")) {
            return "Рыбное блюдо с соусом";
        } else if (meal.getType().equals("Блюдо с мясом")) {
            return "Мясное блюдо с соусом";
        }
        return meal.getType();
    }
}
