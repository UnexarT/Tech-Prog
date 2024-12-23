package ru.tavern.inventory.supplier;

import ru.tavern.inventory.Inventory;

public interface Supplier {
    void resupply(Inventory inventory, int quantity);
}
