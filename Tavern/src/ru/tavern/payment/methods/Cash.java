package ru.tavern.payment.methods;

public class Cash implements PaymentMethod {

    @Override
    public void pay(float sum) {
        System.out.println("Оплата наличными на сумму: " + sum + " руб.");

    }
    @Override
    public void refund(float sum) {
        System.out.println("Возврат средств наличными на сумму: " + sum + " руб.");
    }
}
