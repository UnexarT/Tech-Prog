package ru.tavern.reservation.table;

public class BanquetTable extends Table {

    public BanquetTable(int seats) {
        super(seats);
    }

    @Override
    public String getType() {
        return "Банкетный";
    }
}
