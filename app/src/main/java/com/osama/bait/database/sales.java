package com.osama.bait.database;

public class sales
{
    private int salesId;
    private int areaId;
    private int day ;
    private int month ;
    private int year;
    private double amount ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date ;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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



    public sales( int areaId, int day, int month, int year, double amount,String date) {
        this.areaId  = areaId;
        this.day     = day;
        this.month   = month;
        this.year    = year;
        this.amount  = amount;
        this.date    = date;
    }


    public sales( int areaId ,int month, int year, double amount,String date) {
        this.areaId  = areaId;

        this.month   = month;
        this.year    = year;
        this.amount  = amount;
        this.date    = date;
    }






    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }



    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
