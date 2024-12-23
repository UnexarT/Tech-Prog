package ru.tavern.loyalty;

import ru.tavern.Client;

public class LoyaltyCard extends LoyaltyComponent {

    public LoyaltyCard(Client client) {
        super(client);
    }

    @Override
    public void accruePoints(int points) {
        balance += points;
        System.out.println("Баллы начислены на карту клиента: " + (int) client.getOrder().getSum() + " баллов.");
    }

    @Override
    public void withdrawPoints(int points) {
        if (balance >= points) {
            balance -= points;
            client.getOrder().withdrawPoints(points);
            System.out.println("Баллы списаны с карты клиента: " + points + " баллов.");
        } else {
            System.out.println("Недостаточно баллов на карте клиента.");
        }
    }
}