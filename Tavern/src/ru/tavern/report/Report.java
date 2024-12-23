package ru.tavern.report;

import java.util.Date;

public abstract class Report {

    protected Date beginDate;
    protected Date endDate;
    protected String type;

    public Report(Date beginDate, Date endDate, String type) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.type = type;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getType() {
        return type;
    }

    protected abstract void analizeData();
    public abstract void createReport();

}
