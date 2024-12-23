package ru.tavern.employee.role;

import ru.tavern.Client;
import ru.tavern.employee.Employee;

public abstract class Role {

    private Client client;
    private Employee next;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        if ( next != null) {
            next.getRole().setClient(client);
        }
    }

    public Employee getNext() {
        return next;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    public abstract boolean work(String request);

}
