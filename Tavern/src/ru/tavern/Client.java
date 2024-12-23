package ru.tavern;

import ru.tavern.employee.Employee;
import ru.tavern.loyalty.LoyaltyProgram;
import ru.tavern.order.*;
import ru.tavern.reservation.*;
import ru.tavern.reservation.table.Table;
import ru.tavern.reservation.table.TableManager;

import java.util.Date;

public class Client implements Observer {

    private Order order;
    private String orderStatus;
    private Table table;
    private LoyaltyProgram loyalty;

    public LoyaltyProgram getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(LoyaltyProgram loyalty) {
        this.loyalty = loyalty;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Table getTable() {
        return table;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void reservation(Date date, int numOfPeople, String tableType) {
        Reservation reservation;
        if (tableType.equals("Стандартный") || tableType.equals("VIP")
                || tableType.equals("Банкетный")) {
            if (table != null) {
                table.toFree();
            }
            reservation = new Reservation(date, numOfPeople, tableType);
            table = reservation.makeReservation();
        } else {
            System.out.println("Таких столиков нет!");
        }
    }

    public void occupyTable(int seats) {
        Table bestTable = null;
        int minDifference = Integer.MAX_VALUE;

        // Поиск подходящего стола с минимальной разницей
        for (Table table : TableManager.getInstance().getFreeTables()) {
            int difference = table.getSeats() - seats;
            if (difference >= 0 && difference < minDifference) { // Стол подходит, если разница положительна и минимальна
                bestTable = table;
                minDifference = difference;
            }
        }

        // Если подходящий стол найден
        if (bestTable != null) {
            if (this.table != null) {
                this.table.toFree(); // Освобождаем текущий стол, если он занят
            }
            this.table = bestTable; // Назначаем лучший стол
            bestTable.occupy(); // Занимаем его
            System.out.println(bestTable.getType() + " столик №" + bestTable.getId() + " занят");
        } else {
            // Если подходящего стола нет
            System.out.println("Нет свободных столиков на " + seats + " чел.");
        }
    }

    public void callWaiter(String request, Employee employee) {
        if (request.equals("make_order")) {
            employee.getRole().setClient(this);
        }
        employee.handle(request);
    }

    @Override
    public void handleUpdate(String status) {
        orderStatus = status;
        System.out.println("Observer: Статус заказа - " + status.toLowerCase());
    }

}
