package ru.tavern.inventory;

import ru.tavern.inventory.supplier.Supplier;

public class Inventory {

    protected String productName;
    protected int quantity;
    protected Supplier supplier;
    protected int minQuantity;

    public Inventory(String productName, int quantity, int minQuantity, Supplier supplier) {
        this.productName = productName;
        this.quantity = quantity;
        this.supplier = supplier;
        this.minQuantity = minQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getMinQuantity() {
        return minQuantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void checkAndResupply(int quantityToAdd) {
        System.out.println("Проверка запасов для продукта: " + productName + " (в наличии: " + quantity + " шт.)");

        // Если запас ниже минимального уровня
        if (quantity <= minQuantity) {
            System.out.println("Запас низкий, требуется пополнение...");
            supplier.resupply(this, quantityToAdd);
            System.out.println("Текущее количество после пополнения: " + quantity + " шт.");
        } else {
            System.out.println("Пополнение не требуется, запас в норме.");
        }
    }

    public void consume(int amount) {
        if (amount <= 0) {
            System.out.println("Количество для расхода должно быть положительным!");
            return;
        }

        if (quantity >= amount) {
            quantity -= amount;
            System.out.println("Расходовано " + amount + " шт. " + productName + ". Остаток: " + quantity + " шт.");
        } else {
            System.out.println("Недостаточно товара " + productName + " для расхода " + amount + " шт.");
        }
    }
}
