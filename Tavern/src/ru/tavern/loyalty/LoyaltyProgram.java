package ru.tavern.loyalty;

import ru.tavern.Client;
import java.util.ArrayList;

public class LoyaltyProgram extends LoyaltyComponent {

    private ArrayList<LoyaltyComponent> components;

    public LoyaltyProgram(Client client) {
        super(client);
        components = new ArrayList<>();
    }

    @Override
    public void accruePoints(int points) {
        if (components.size() != 0) {
            System.out.println("Начисление баллов в программу лояльности клиента");
            for (LoyaltyComponent component : components) {
                component.accruePoints(points);
            }
        } else {
            System.out.println("Клиент не имеет карт в системе лояльности");
        }
    }

    @Override
    public void withdrawPoints(int points) {
        if (components.size() != 0) {
            System.out.println("Списание баллов из программы лояльности клиента");
            for (LoyaltyComponent component : components) {
                component.withdrawPoints(points);
            }
        } else {
            System.out.println("Клиент не имеет карт в системе лояльности");
        }
    }

    @Override
    public void add(LoyaltyComponent component) {
        components.add(component);
    }

    @Override
    public void remove(LoyaltyComponent component) {
        components.remove(component);
    }

    @Override
    public LoyaltyComponent getChild(int index) {
        if (index >= 0 && index < components.size()) {
            return components.get(index);
        }
        return null;
    }

    @Override
    public int getBalance() {
        if (components.size() != 0) {
            return getChild(0).balance;
        }
        return 0;
    }

    public void displayBalances() {
        int count = 0;
        for (LoyaltyComponent component : components) {
            System.out.println("Баланс карты №" + ++count + ": " + component.getBalance());
        }
    }
}
