package ru.tavern.loyalty;

import ru.tavern.Client;

public abstract class LoyaltyComponent {
    protected Client client;
    protected int balance;

    public LoyaltyComponent(Client client) {
        this.client = client;
        balance = 0;
    }

    public abstract void accruePoints(int points);
    public abstract void withdrawPoints(int points);

    public void add(LoyaltyComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(LoyaltyComponent component) {
        throw new UnsupportedOperationException();
    }

    public LoyaltyComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    public int getBalance() {
        return balance;
    }
}