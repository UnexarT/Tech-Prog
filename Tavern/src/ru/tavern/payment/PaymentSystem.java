package ru.tavern.payment;

import ru.tavern.payment.methods.PaymentMethod;

public class PaymentSystem {

    protected PaymentMethod payment;
    protected float sum;
    protected static float totalSum;

    public PaymentSystem(PaymentMethod payment, float sum) {
        this.payment = payment;
        this.sum = sum;
    }

    public static float getTotalSum() {
        return totalSum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
    public float getSum() {
        return sum;
    }

    public void processPayment() {
        payment.pay(sum);
        totalSum += sum;
    }

    public void processRefund() {
        payment.refund(sum);
        sum = 0;
    }

}
