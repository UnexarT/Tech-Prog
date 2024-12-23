package ru.tavern.reservation.table;

public class VIPTable extends Table {

    public VIPTable(int seats) {
        super(seats);
    }

    @Override
    public String getType() {
        return "VIP";
    }
}