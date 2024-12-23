package ru.tavern.reservation.table;

import ru.tavern.reservation.table.state.Free;
import ru.tavern.reservation.table.state.Occupied;
import ru.tavern.reservation.table.state.State;

public abstract class Table {

    private int id;
    private int seats;
    private State state;

    public Table(int seats) {
        this.id = TableManager.getInstance().getSize() + 1;
        this.seats = seats;
        this.state = new Free();
    }

    public int getId() {
        return id;
    }

    public int getSeats() {
        return seats;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public abstract String getType();

    public void occupy() {
        setState(new Occupied());
    }

    public void toFree() {
        setState(new Free());
    }
}
