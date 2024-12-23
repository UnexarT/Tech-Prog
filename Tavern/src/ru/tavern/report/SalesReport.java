package ru.tavern.report;

import ru.tavern.order.Order;

import java.util.Date;

public class SalesReport extends Report {

    private float sum = 0;

    public SalesReport(Date beginDate, Date endDate) {
        super(beginDate, endDate, "Отчет продаж");
    }

    @Override
    protected void analizeData() {
        for (Order order: Order.getOrdersStory()) {
            if ((order.getCreationTime().after(beginDate) || order.getCreationTime().equals(beginDate)) &&
                    (order.getCreationTime().before(endDate) || order.getCreationTime().equals(beginDate)) ) {
                sum += order.getPayment().getSum();
            }
        }
    }

    @Override
    public void createReport() {
        analizeData();
        System.out.println(type + ": за период от " + beginDate + " до " + endDate +
                " касса составила " + sum + " р.");
    }

}
