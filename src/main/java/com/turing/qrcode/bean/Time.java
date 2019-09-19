package com.turing.qrcode.bean;

import java.util.Date;

public class Time {
    private Integer timeId;

    private Integer studetId;

    private Integer state;

    private Date datetime;

    private Integer tableId;

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getStudetId() {
        return studetId;
    }

    public void setStudetId(Integer studetId) {
        this.studetId = studetId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
}