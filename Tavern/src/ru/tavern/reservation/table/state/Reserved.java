package ru.tavern.reservation.table.state;

public class Reserved implements State {

    @Override
    public String getType() {
        return "зарезервирован";
    }

}
