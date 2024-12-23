import ru.tavern.Client;
import ru.tavern.employee.Employee;
import ru.tavern.employee.role.*;
import ru.tavern.inventory.Inventory;
import ru.tavern.inventory.supplier.*;
import ru.tavern.menu.Menu;
import ru.tavern.menu.dish.*;
import ru.tavern.menu.dish.meal.*;
import ru.tavern.reservation.table.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        //Зададим рабочий персонал
        Employee waiter1 = new Employee("Иван", new Waiter(), 1);
        Employee waiter2 = new Employee("Петр", new Waiter(), 3);

        Employee manager = new Employee("Михаил", new Manager(), 5);

        Employee bartender = new Employee("Владислав", new Bartender(), 2);

        Employee chef = new Employee("Максим", new Chef(), 6);

        //Зададим цепочку последовательностей
        waiter1.setNext(bartender);
        bartender.setNext(chef);
        chef.setNext(waiter2);

        Dish[] dishes = {
                new Drink("Водка", 300),
                new Drink("Пиво", 200),
                new Drink("Лимонад", 100),
                new FishMeal("Жаренный лосось", 800),
                new FishMeal("Суши филадельфия", 500),
                new FishMeal("Сюрстрёминг", 1000),
                new MeatMeal("Cтейк из баранины", 800),
                new MeatMeal("Каре ягненка", 700),
                new MeatMeal("Котлета из хлеба", 150),
        };

        //Зададим меню
        for (Dish dish: dishes) {
            Menu.getInstance().addDish(dish);
        }
        //Menu.getInstance().displayMenu();

        //Проверка добавления новой позиции менеджером
        /*Menu.getInstance().displayMenu();
        for (int i = 1; i <= 6; i++){
            manager.handle("add_dish");
            Menu.getInstance().displayMenu();
        }*/
        //Зададим продуктовую корзину шефу и бармену
        ((Chef) chef.getRole()).addProduct(new Inventory(
                "Мясо",
                12,
                3,
                new AutoSupply()));
        ((Chef) chef.getRole()).addProduct(new Inventory(
                "Рыба",
                12,
                3,
                new RegularSupply()));
        for (Dish dish: Menu.getInstance().getMenu()) {
            if (dish.getType().equals("Напиток")) {
                ((Bartender) bartender.getRole()).addProduct(new Inventory(
                        dish.getName(),
                        3500,
                        300,
                        new RegularSupply()));
            }
        }

        //Зададим столики
        TableManager.getInstance().addTable(new BanquetTable(10));
        TableManager.getInstance().addTable(new BanquetTable(8));
        TableManager.getInstance().addTable(new VIPTable(2));
        TableManager.getInstance().addTable(new VIPTable(4));
        TableManager.getInstance().addTable(new StandartTable(4));
        TableManager.getInstance().addTable(new StandartTable(4));
        //TableManager.getInstance().displayTables();

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());
        clients.add(new Client());
        clients.add(new Client());
        clients.add(new Client());

        //clients.get(0).reservation(Date.valueOf(LocalDate.now().plusDays(1)), 2, "Стандартный");
        System.out.println("Свободные столики:");
        for (Table table: TableManager.getInstance().getFreeTables())
            System.out.println(table.getType() + " cтолик №" + table.getId() + " на " + table.getSeats() + " чел.");
        System.out.println();

        String[] tables = {"VIP", "Стандартный", "Банкетный"};

        System.out.println("Проверка резервирования столиков:");
        for (Client client: clients) {
            if (client.getTable() == null) {
                client.reservation(Date.valueOf(LocalDate.now().plusDays(
                        ThreadLocalRandom.current().nextInt(1, 7))),
                        ThreadLocalRandom.current().nextInt(1, 8),
                        tables[ThreadLocalRandom.current().nextInt(1, 3)]);
            }
        }

        /*String[] requests = {"cook", "make_drink", "delivery", "pay", "sosal?"};

        //Проверка подбора столиков и обслуживания клиентов
        for (Client client: clients) {
            if (client.getTable() == null) {
                client.occupyTable(ThreadLocalRandom.current().nextInt(1, 10));
            }
            if (client.getTable() != null && client.getTable().getState().getType().equals("занят")) {
                client.callWaiter("make_order",  waiter1);
                for (String request: requests) {
                    waiter1.handle(request);
                }
            }
        }*

        //Проверка системы лояльности
        clients.get(2).occupyTable(4);
        for (int i = 0; i < 3; i++){
            clients.get(2).callWaiter("make_order", waiter1);
            clients.get(2).callWaiter("pay", waiter1);
            if (i == 2) {
                clients.get(2).callWaiter("refund", waiter1);
            }
        }

        //Проверка работы менеджера
        waiter1.setNext(manager);
        manager.setNext(waiter2);
        manager.handle("remove_dish");
        manager.handle("sales_report");*/


    }
}