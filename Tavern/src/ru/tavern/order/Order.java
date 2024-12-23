package ru.tavern.order;

import ru.tavern.menu.dish.Dish;
import ru.tavern.payment.PaymentSystem;
import ru.tavern.payment.methods.*;

import java.util.ArrayList;
import java.util.Date;

public class Order implements Observable {

    private ArrayList<Dish> currentDishies;
    //private ArrayList<Dish> cookedDishies;
    private Date creationTime;
    private String status;
    private ArrayList<Observer> observers;
    private PaymentSystem payment;
    private float sum;
    private static ArrayList<Order> ordersStory = new ArrayList<>();

    public Order() {
        currentDishies = new ArrayList<>();
        creationTime = new Date();
        //cookedDishies = new ArrayList<>();
        observers = new ArrayList<>();
        status = "Создание заказа";
        ordersStory.add(this);
    }

    /*public ArrayList<Dish> getCookedDishies() {
        return cookedDishies;
    }

    public Dish getLastCooked() {
        return cookedDishies.getLast();
    }*/

    public static ArrayList<Order> getOrdersStory() {
        return ordersStory;
    }

    public ArrayList<Dish> getCurrentDishies() {
        return currentDishies;
    }

    public float getSum() {
        return sum;
    }

    public void withdrawPoints(int points) {
        sum -= points / 100;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void displayCurrentDishies() {
        System.out.println("Ваш заказ:");
        for (Dish dish: currentDishies) {
            System.out.println("- " + dish.getName() + " " + dish.getPrice() +" р.");
        }
        System.out.println("Итого: " + getSum() + " р.\n");
    }

    public String getStatus() {
        return status;
    }

    public void setPayment(PaymentMethod method) {
        payment = new PaymentSystem(method, sum);
    }

    public PaymentSystem getPayment() {
        return payment;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.handleUpdate(status);
        }
    }

    public void addDish(Dish dish) {
        currentDishies.add(dish);
        sum += dish.getPrice();
    }

    public void removeDish(Dish dish) {
        currentDishies.remove(dish);
        sum -= dish.getPrice();
    }

    public float getCurrentSum() {
        float sum = 0;
        if (currentDishies.size() != 0) {
            for (Dish dish: currentDishies) {
                sum += dish.getPrice();
            }
        }
        return sum;
    }

    public void updateStatus(String status) {
        this.status = status;
        notifyObservers();
    }

}
