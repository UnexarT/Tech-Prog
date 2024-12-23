package ru.tavern.menu.dish.meal.decorator;

import ru.tavern.menu.dish.meal.Meal;

public class GarnishDecorator extends MealDecorator {

    public GarnishDecorator(Meal meal){
        super(meal);
    }

    @Override
    public String getName() {
        return meal.getName() + " + гарнир";
    }

    @Override
    public float getPrice() {
        return meal.getPrice() + 90;
    }

    @Override
    public String getType() {
        if (meal.getType().equals("Блюдо с рыбой")) {
            return "Рыбное блюдо с гарниром";
        } else if (meal.getType().equals("Блюдо с мясом")) {
            return "Мясное блюдо с гарниром";
        }
        return meal.getType();
    }

}