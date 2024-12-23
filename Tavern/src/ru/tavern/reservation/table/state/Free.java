package ru.tavern.reservation.table.state;

public class Free implements State {

    @Override
    public String getType() {
        return "свободный";
    }

}
