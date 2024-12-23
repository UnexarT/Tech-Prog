package ru.tavern.payment.methods;

public class OnlinePay implements PaymentMethod {

    @Override
    public void pay(float sum) {
        System.out.println("Оплата онлайн на сумму: " + sum + " руб.");
    }
    @Override
    public void refund(float sum) {
        System.out.println("Возврат средств онлайн на сумму: " + sum + " руб.");
    }
}
