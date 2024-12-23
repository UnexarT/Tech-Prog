package ru.tavern.reservation.table;

import ru.tavern.menu.dish.Dish;

import java.util.ArrayList;

public class TableManager {

    private static TableManager instance;
    private static ArrayList<Table> tables;

    private TableManager() {
        tables = new ArrayList<>();
    }

    public static TableManager getInstance() {
        if (instance == null) {
            instance = new TableManager();
        }
        return instance;
    }

    public ArrayList<Table> getFreeTables() {
        ArrayList<Table> freeTables = new ArrayList<>();
        for (Table table: tables) {
            if (table.getState().getType().equals("свободный")) {
                freeTables.add(table);
            }
        }
        return freeTables;
    }

    public int getSize() {
        return tables.size();
    }

    public void displayTables() {
        System.out.println("Столы:");
        for (Table table: tables) {
            System.out.println(table.getId() + ") " + table.getType() + " столик на "
                    + table.getSeats() + " чел. " + table.getState().getType());
        }
        System.out.println();
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void removeTable(int id) {
        tables.remove(id);
    }

}
