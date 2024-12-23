package ru.tavern.employee.role;

import ru.tavern.inventory.Inventory;
import ru.tavern.menu.dish.Dish;

import java.util.ArrayList;

public class Chef extends Role {

    public static ArrayList<Inventory> inventory = new ArrayList<>();

    public void addProduct(Inventory product) {
        inventory.add(product);
        //System.out.printf("Добавлено " + inventory.getLast().getProductName() + "\n");
    }

    public void removeProduct(Inventory product) {
        inventory.remove(product);
    }

    private void cookingProcess() {
        for (Dish dish : getClient().getOrder().getCurrentDishies()) {
            if (dish.getType().matches(".*[Бб]люд.*")) {
                productCheck();
                System.out.println(dish.getType() + " " + dish.getName() + " готов");
            }
        }
        if (getClient().getOrder().getStatus().equals("Напитки готовы")) {
            getClient().getOrder().updateStatus("Заказ готов к выносу официантом");
        } else {
            getClient().getOrder().updateStatus("Блюда готовы");
        }
    }

    private void productCheck() {
        for (Inventory product: inventory) {
            if (product.getProductName().matches(".*[Рр]ыб.*|.*[Мм]яс.*")) {
                product.checkAndResupply(10);
                product.consume(2);
            }
        }
    }

    @Override
    public boolean work(String request) {

        switch (request) {
            case "cook":
                cookingProcess();
                return true;
            default:
                return false;
        }
    }
}
