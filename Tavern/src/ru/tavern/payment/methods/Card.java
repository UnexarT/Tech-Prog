package ru.tavern.payment.methods;

public class Card implements PaymentMethod {

    @Override
    public void pay(float sum) {
        System.out.println("Оплата картой на сумму: " + sum + " руб.");
    }
    @Override
    public void refund(float sum) {
        System.out.println("Возврат средств на карту на сумму: " + sum + " руб.");
    }
}
