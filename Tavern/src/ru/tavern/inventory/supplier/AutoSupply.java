package ru.tavern.inventory.supplier;

import ru.tavern.inventory.Inventory;

public class AutoSupply implements Supplier {

    @Override
    public void resupply(Inventory inventory, int quantityToAdd) {
        if (inventory.getQuantity() <= inventory.getMinQuantity()) {
            inventory.setQuantity(inventory.getQuantity() + quantityToAdd);
            System.out.println("Автопополнение: " + inventory.getProductName() + " на " + quantityToAdd + " шт.");
        } else {
            System.out.println("Автопополнение не требуется для продукта: " + inventory.getProductName());
        }
    }
}
