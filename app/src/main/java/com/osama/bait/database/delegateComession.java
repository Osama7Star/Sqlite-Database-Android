package com.osama.bait.database;

public class delegateComession
{
    private int delegatComessionId;
    private int delegateId ;
    private int areaId ;
    private int month ;
    private int year;
    private String date;
    private int amount ;

    public delegateComession(){}

    public delegateComession(int delegateSalesId, int delegateId, int areaId, String date, int amount) {
        this.delegatComessionId = delegateSalesId;
        this.delegateId = delegateId;
        this.areaId = areaId;
        this.date = date;
        this.amount = amount;
    }

    public int getDelegateSalesId() {
        return delegatComessionId;
    }

    public void setDelegateSalesId(int delegatComessionId) {
        this.delegatComessionId = delegatComessionId;
    }

    public int getDelegateId() {
        return delegateId;
    }

    public void setDelegateId(int delegateId) {
        this.delegateId = delegateId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



    public int getDelegatComessionId() {
        return delegatComessionId;
    }

    public void setDelegatComessionId(int delegatComessionId) {
        this.delegatComessionId = delegatComessionId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
