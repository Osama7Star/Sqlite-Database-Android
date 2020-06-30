package com.osama.bait.database;

public class delegateSales
{
    private int delegateSalesId;
    private int delegateId ;
    private int areaId ;
    private String date;
    private int month ;
    private int year;
    private int amount ;
    private int firstArea,secondArea,thirdArea,foruthArea,fifthArea;
    private double comession ;
    public double getComession() {
        return comession;
    }

    public void setComession(double comession) {
        this.comession = comession;
    }




    public int getFirstArea() {
        return firstArea;
    }

    public void setFirstArea(int firstArea) {
        this.firstArea = firstArea;
    }

    public int getSecondArea() {
        return secondArea;
    }

    public void setSecondArea(int secondArea) {
        this.secondArea = secondArea;
    }

    public int getThirdArea() {
        return thirdArea;
    }

    public void setThirdArea(int thirdArea) {
        this.thirdArea = thirdArea;
    }

    public int getForuthArea() {
        return foruthArea;
    }

    public void setForuthArea(int foruthArea) {
        this.foruthArea = foruthArea;
    }

    public int getFifthArea() {
        return fifthArea;
    }

    public void setFifthArea(int fifthArea) {
        this.fifthArea = fifthArea;
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


    public delegateSales(){}
    public delegateSales(int delegateSalesId, int delegateId, int areaId, String date,int month,int year, int amount) {
        this.delegateSalesId = delegateSalesId;
        this.delegateId      = delegateId;
        this.areaId          = areaId;
        this.date            = date;
        this.month           = month ;
        this.year            = month ;
        this.amount          = amount;

    }

    public int getDelegateSalesId() {
        return delegateSalesId;
    }

    public void setDelegateSalesId(int delegateSalesId) {
        this.delegateSalesId = delegateSalesId;
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


}
