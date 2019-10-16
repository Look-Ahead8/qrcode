package com.turing.qrcode.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Time {
    @JsonIgnore
    private Integer timeId;

    private Integer studetId;

    private Integer state;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
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