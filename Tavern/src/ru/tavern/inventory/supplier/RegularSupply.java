package ru.tavern.inventory.supplier;

import ru.tavern.inventory.Inventory;

public class RegularSupply implements Supplier {

    @Override
    public void resupply(Inventory inventory, int quantityToAdd) {
        if (quantityToAdd <= 0) {
            System.out.println("Некорректное количество для пополнения!");
            return;
        }

        inventory.setQuantity(inventory.getQuantity() + quantityToAdd);
        System.out.println("Регулярная поставка: добавлено " + quantityToAdd + " шт. продукта " + inventory.getProductName());
    }
}