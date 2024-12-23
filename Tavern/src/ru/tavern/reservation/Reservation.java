package ru.tavern.reservation;

import ru.tavern.reservation.table.Table;
import ru.tavern.reservation.table.TableManager;
import ru.tavern.reservation.table.state.Reserved;

import java.util.ArrayList;
import java.util.Date;

public class Reservation {

    private Date reservationTime;
    private int numOfPeople;
    private String type;

    public Reservation(Date reservationTime, int numOfPeople, String type) {
        this.reservationTime = reservationTime;
        this.numOfPeople = numOfPeople;
        this.type = type;
    }

    public Table makeReservation() {
        TableManager manager = TableManager.getInstance();
        ArrayList<Table> freeTables = manager.getFreeTables();
        if (freeTables.isEmpty()) {
            System.out.println("Все столики заняты!");
        } else {
            for (Table table : manager.getFreeTables()) {
                if ((table.getSeats() >= getNumOfPeople()) && (table.getType().equals(getType()))) {
                    table.setState(new Reserved());
                    System.out.println(table.getType() + " столик " + table.getId()
                            + " зарезервирован на " + getReservationTime());
                    return table;
                }
            }
            System.out.println("Нет свободных столов типа: " + getType()
                    + " на " + getNumOfPeople() + " чел.");
        }
        return null;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public String getType() {
        return type;
    }
}
