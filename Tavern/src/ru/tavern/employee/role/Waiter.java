package ru.tavern.employee.role;

import ru.tavern.loyalty.*;
import ru.tavern.menu.Menu;
import ru.tavern.menu.dish.meal.Meal;
import ru.tavern.menu.dish.meal.decorator.*;
import ru.tavern.order.Order;
import ru.tavern.payment.methods.*;

import java.util.Scanner;

public class Waiter extends Role {

    private int getValidChoice(Scanner scn) {
        while (true) {
            String input = scn.nextLine();

            if (!input.matches("\\d+")) {
                System.out.println("Введите корректный номер!");
                continue;
            }
            int choice = Integer.parseInt(input);
            return choice;
        }
    }

    private void displayMenu() {
        Menu.getInstance().displayMenu();
    }

    private void processOrder() {
        Scanner scn = new Scanner(System.in);
        Order order = new Order();

        while (true) {
            Menu.getInstance().displayMenu();
            System.out.println("Введите номер блюда, чтобы добавить в заказ (или 0 для завершения): ");

            int choice = getValidChoice(scn);

            if (choice == 0) {
                System.out.println("Ваш заказ завершен.");
                if (!order.getCurrentDishies().isEmpty()) {
                    order.addObserver(getClient());
                    order.updateStatus("Заказ создан");
                    getClient().setOrder(order);
                    getClient().getOrder().displayCurrentDishies();
                }
                break;
            } else if (choice < 1 || choice > Menu.getInstance().getMenu().size()) {
                System.out.println("Такого блюда нет в меню. Попробуйте снова.");
            } else {
                order.addDish(Menu.getInstance().getMenu().get(choice - 1));
                handleAdditions(order, scn);
            }
            if (!order.getCurrentDishies().isEmpty()) {
                System.out.println("Вы добавили в заказ: " + order.getCurrentDishies().getLast().getName());
            }
        }

    }

    private void handleAdditions(Order order, Scanner scn) {
        if (order.getCurrentDishies().getLast().getType().matches(".*[Бб]люд.*")) {
            System.out.println("У нас есть добавки к блюду: " + order.getCurrentDishies().getLast().getName());
            System.out.println("0 - без добавок\n1 - гарнир\n2 - фирменный соус");

            while (true) {
                System.out.println("Введите номер добавки, чтобы добавить к блюду (или 0 для завершения):");

                int choice = getValidChoice(scn);

                if (choice == 0) {
                    System.out.println("Добавление добавок завершено.");
                    break;
                } else if (choice == 1) {
                    order.getCurrentDishies().set(order.getCurrentDishies().size() - 1,
                            new GarnishDecorator((Meal) order.getCurrentDishies().getLast()));
                } else if (choice == 2) {
                    order.getCurrentDishies().set(order.getCurrentDishies().size() - 1,
                            new SauseDecorator((Meal) order.getCurrentDishies().getLast()));
                } else {
                    System.out.println("Такой добавки нет в меню. Попробуйте снова.");
                }
            }
        }
    }

    private void processDelivery() {
        if (getClient().getOrder().getStatus().equals("Заказ готов к выносу официантом")) {
            getClient().getOrder().updateStatus("Заказ вынесен");
            System.out.println("Заказ клиента за столиком №" + getClient().getTable().getId() +
                    " вынесен");
        } else {
            System.out.println("Невозможная операция. Заказ ещё не готов к выносу официантом");
        }
    }

    private void processPayment() {
        Scanner scn = new Scanner(System.in);
        boolean pointsFlag = false;

        if (getClient().getLoyalty() != null && getClient().getLoyalty().getChild(0) != null &&
                getClient().getLoyalty().getBalance() >= getClient().getOrder().getSum() * 20) {
            pointsFlag = handleLoyaltyPoints(scn);
        }

        if (handlePaymentMethod(scn)) {
            if (getClient().getLoyalty() == null) {
                handleLoyaltyEnrollment(scn);
            } else if (!pointsFlag) {
                handleLoyaltyAdditions(scn);
            }
        }
    }

    private boolean handleLoyaltyPoints(Scanner scn) {
        while (true) {
            System.out.println("У вас достаточно баллов для списания 20% стоимости заказа. Хотите списать?");
            System.out.println("1 - Списать\n2 - Не списывать");

            int choice = getValidChoice(scn);

            if (choice == 1) {
                getClient().getLoyalty().withdrawPoints((int) getClient().getOrder().getSum() * 20);
                return true;
            } else if (choice == 2) {
                System.out.println("Вы отказались от списания баллов.");
                return false;
            } else {
                System.out.println("Введите корректный номер!");
            }
        }
    }

    private boolean handlePaymentMethod(Scanner scn) {
        while (true) {
            System.out.println("Выберите способ оплаты:\n1 - Онлайн\n2 - Наличные\n3 - Картой");
            int choice = getValidChoice(scn);

            switch (choice) {
                case 1:
                    getClient().getOrder().setPayment(new OnlinePay());
                    break;
                case 2:
                    getClient().getOrder().setPayment(new Cash());
                    break;
                case 3:
                    getClient().getOrder().setPayment(new Card());
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
                    continue;
            }

            getClient().getOrder().getPayment().processPayment();
            getClient().getOrder().updateStatus("Заказ оплачен");
            System.out.println("Cтатус заказа: " + getClient().getOrderStatus());
            return true;
        }
    }

    private void handleLoyaltyEnrollment(Scanner scn) {
        while (true) {

            System.out.println("Добавить вас в систему лояльности?");
            System.out.println("1 - добавить\n2 - не добавлять");

            int choice = getValidChoice(scn);

            if (choice == 1) {
                getClient().setLoyalty(new LoyaltyProgram(getClient()));
                getClient().getLoyalty().add(new LoyaltyCard(getClient()));
                System.out.println("Вы добавленны в систему лояльности!");
                getClient().getLoyalty().accruePoints((int) getClient().getOrder().getSum());
                getClient().getLoyalty().displayBalances();
                break;
            } else if (choice == 2) {
                System.out.println("Вы отказались от системы лояльности");
                break;
            } else {
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    private void handleLoyaltyAdditions(Scanner scn) {
        System.out.println("Мы нашли вас в системе лояльности");
        if (getClient().getLoyalty().getChild(0) == null) {
            System.out.println("В вашем профиле нет карт лояльности");
        }
        while (true) {

            System.out.println("Добавить новую карту к вашему профилю в сиcтеме лояльности?");
            System.out.println("1 - добавить\n2 - не добавлять");

            int choice = getValidChoice(scn);

            if (choice == 1) {
                getClient().getLoyalty().add(new LoyaltyCard(getClient()));
                System.out.println("Новая карта добавленны в систему лояльности!");
                getClient().getLoyalty().accruePoints((int) getClient().getOrder().getSum());
                getClient().getLoyalty().displayBalances();
                break;
            } else if (choice == 2) {
                getClient().getLoyalty().accruePoints((int) getClient().getOrder().getSum());
                getClient().getLoyalty().displayBalances();
                break;
            } else {
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    private void processRefund() {
        if (!getClient().getOrder().getStatus().equals("Заказ оплачен")) {
            System.out.println("Невозможно вернуть деньги. Заказ ещё не оплачен");
        } else {
            getClient().getOrder().getPayment().processRefund();
            if (getClient().getLoyalty() != null &&
                    getClient().getOrder().getSum() != getClient().getOrder().getCurrentSum()) {
                getClient().getLoyalty().accruePoints((int) getClient().getOrder().getCurrentSum() * 20);
            } else if (getClient().getLoyalty() != null && getClient().getLoyalty().getChild(0) != null) {
                getClient().getLoyalty().withdrawPoints((int) getClient().getOrder().getSum());
                getClient().getLoyalty().displayBalances();
            }
            getClient().getOrder().updateStatus("Средства за заказ возвращены");
        }
    }



    @Override
    public boolean work(String request) {
        Scanner scn = new Scanner(System.in);

        switch (request) {
            case "get_menu":
                displayMenu();
                return true;
            case "make_order":
                processOrder();
                return true;
            case "delivery":
                processDelivery();
                return true;
            case "pay":
                processPayment();
                return true;
            case "refund":
                processRefund();
                return true;
            default:
                return false;
        }
    }
}
