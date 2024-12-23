package ru.tavern.employee;

public interface EmployeeHandler {

    void setNext(EmployeeHandler handler);
    void handle(String request);

}
