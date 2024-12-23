package ru.tavern.employee.role;

import ru.tavern.inventory.Inventory;
import ru.tavern.menu.dish.Dish;

import java.util.ArrayList;

public class Bartender extends Role {

    public static ArrayList<Inventory> inventory = new ArrayList<>();

    public void addProduct(Inventory product) {
        inventory.add(product);
        //System.out.printf("Добавлено " + inventory.getLast().getProductName() + "\n");
    }

    public void removeProduct(Inventory product) {
        inventory.remove(product);
    }

    private void shakeProcess() {
        for (Dish dish : getClient().getOrder().getCurrentDishies()) {
            if (!dish.getType().matches(".*[Бб]люд.*")) {
                productCheck(dish.getName());
                System.out.println(dish.getType() + " " + dish.getName() + " готов");
            }
        }
        if (getClient().getOrder().getStatus().equals("Блюда готовы")) {
            getClient().getOrder().updateStatus("Заказ готов к выносу официантом");
        } else {
            getClient().getOrder().updateStatus("Напитки готовы");
        }
    }

    private void productCheck(String dishName) {
        for (Inventory product: inventory) {
            if (dishName.equals(product.getProductName())) {
                product.checkAndResupply(3000);
                product.consume(300);
            }
        }
    }

    @Override
    public boolean work(String request) {

        switch (request) {
            case "make_drink":
                shakeProcess();
                return true;
            default:
                return false;
        }
    }
}
