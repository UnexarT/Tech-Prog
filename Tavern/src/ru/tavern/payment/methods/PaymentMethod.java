package ru.tavern.payment.methods;

public interface PaymentMethod {

    void pay(float sum);
    void refund(float sum);

}
