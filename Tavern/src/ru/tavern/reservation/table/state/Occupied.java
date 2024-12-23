package ru.tavern.reservation.table.state;

public class Occupied implements State {

    @Override
    public String getType() {
        return "занят";
    }

}
