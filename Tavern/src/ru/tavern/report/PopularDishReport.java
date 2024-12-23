package ru.tavern.report;

import ru.tavern.menu.Menu;
import ru.tavern.menu.dish.Dish;
import ru.tavern.order.Order;

import java.util.Date;

public class PopularDishReport extends Report {

    private static Dish[] menu = Menu.getInstance().getMenu().toArray(new Dish[0]);
    private int[] counts = new int[menu.length];

    public PopularDishReport(Date beginDate, Date endDate) {
        super(beginDate, endDate, "Отчет популярности блюд");
    }

    @Override
    protected void analizeData() {
        Dish[] currentDishies;
        for (Order order: Order.getOrdersStory()) {
            if ((order.getCreationTime().after(beginDate) || order.getCreationTime().equals(beginDate)) &&
                    (order.getCreationTime().before(endDate) || order.getCreationTime().equals(beginDate)) ) {
                currentDishies = order.getCurrentDishies().toArray(new Dish[0]);
                for (Dish dish: currentDishies) {
                    for (int i = 0; i < menu.length; i++){
                        if (dish.getName().equals(menu[i].getName())) {
                            counts[i]++;
                        }
                    }
                }
            }
        }
    }

    private Dish getMostPopular() {
        int max = 0;
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
                index = i;
            }
        }
        return menu[index];
    }

    public Dish getLeastPopular() {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] < min) {
                min = counts[i];
                index = i;
            }
        }
        return menu[index];
    }

    @Override
    public void createReport() {
        analizeData();
        System.out.println(type + ": за период от " + beginDate + " до " + endDate +
                "\n самой продаваемой позицией было " + getMostPopular().getName()  +
                "\n самой менее продаваемой позицией было " + getLeastPopular().getName());
    }
}
