package ru.tavern.employee.role;

import ru.tavern.menu.Menu;
import ru.tavern.menu.dish.*;
import ru.tavern.menu.dish.meal.*;
import ru.tavern.report.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Manager extends Role {

    private Report report;

    private float getValidChoice(Scanner scn, String string, int min, int max) {
        while (true) {

            System.out.println(string);

            String input = scn.nextLine();

            if (!input.matches("\\d+")) {
                System.out.println("Введите корректный номер!");
                continue;
            }

            float choice = Float.parseFloat(input);

            if (choice < min || choice > max) {
                System.out.println("Некорректный ответ. Попробуйте снова.");
            } else {
                return choice;
            }
        }
    }

    private void addDish() {
        Scanner scn = new Scanner(System.in);

        int typeChoise = (int) getValidChoice(
                scn,
                "Какую позицию вы хотите добавить?\n" +
                        "1 - Напиток\n" +
                        "2 - Мясное блюдо\n" +
                        "3 - Рыбное блюдо",
                1,
                3);

        System.out.println("Введите название позиции меню: ");
        String dishName = scn.nextLine();
        float dishPrice = getValidChoice(
                scn,
                "Введите цену новой позиции: ",
                10,
                Integer.MAX_VALUE);

        switch (typeChoise) {
            case 1:
                Menu.getInstance().addDish(new Drink(dishName, dishPrice));
                break;
            case 2:
                Menu.getInstance().addDish(new MeatMeal(dishName, dishPrice));
                break;
            case 3:
                Menu.getInstance().addDish(new FishMeal(dishName, dishPrice));
                break;
        }
        System.out.println("В меню добавлена позиция " + dishName + " ценой в " + dishPrice + " р.");
    }

    private void removeDish() {
        report = new PopularDishReport(Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(1)));
        report.createReport();
        Dish looser = ((PopularDishReport) report).getLeastPopular();
        System.out.println("Позиция " + looser.getName() + " удалена из меню");
        Menu.getInstance().removeDish(looser);
        Menu.getInstance().displayMenu();
    }

    private void salesReport() {
        LocalDate today = LocalDate.now();
        report = new SalesReport(Date.valueOf(today), Date.valueOf(today.plusDays(1)));
        report.createReport();
    }



    @Override
    public boolean work(String request) {
        Scanner scn = new Scanner(System.in);
        switch (request) {
            case "add_dish":
                addDish();
                return true;
            case "remove_dish":
                removeDish();
                return true;
            case "sales_report":
                salesReport();
                return true;
            default:
                return false;
        }
    }
}
