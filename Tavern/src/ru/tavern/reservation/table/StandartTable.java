package ru.tavern.reservation.table;


public class StandartTable extends Table {

    public StandartTable(int seats) {
        super(seats);
    }

    @Override
    public String getType() {
        return "Стандартный";
    }
}
