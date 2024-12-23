package ru.tavern.menu;

import ru.tavern.menu.dish.Dish;
import java.util.ArrayList;

public class Menu {

    private static Menu instance;
    private ArrayList<Dish> menu;

    private Menu() {
        menu = new ArrayList<>();
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void addDish(Dish dish) {
        menu.add(dish);
    }

    public void removeDish(Dish dish) {
        menu.remove(dish);
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public void displayMenu() {
        System.out.println("Меню:");
        int iter = 0;
        for (Dish dish: menu) {
            iter += 1;
            System.out.println(iter + ") " + dish.getName() + " " + dish.getPrice() +" р.");
        }
        System.out.println();
    }
}
