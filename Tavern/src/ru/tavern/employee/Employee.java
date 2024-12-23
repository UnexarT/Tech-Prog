package ru.tavern.employee;

import ru.tavern.employee.role.Role;

public class Employee implements EmployeeHandler {

    protected String name;
    protected Role role;
    protected EmployeeHandler next;
    protected float experience;

    public Employee(String name, Role role, float experience) {
        this.name = name;
        this.role = role;
        this.experience = experience;
    }

    public Role getRole() {
        return role;
    }

    public EmployeeHandler getNext() {
        return next;
    }

    public float getExperience() {
        return experience;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setNext(EmployeeHandler handler) {
        next = handler;
        role.setNext( (Employee) handler);
    }

    @Override
    public void handle(String request) {
        if (next != null && !role.work(request)) {
            next.handle(request);
        } else if (next == null) {
            System.out.println("Задача \"" + request + "\" не может быть выполнена.");
        }
    }
}
