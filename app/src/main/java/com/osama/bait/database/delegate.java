package com.osama.bait.database;

import android.net.Uri;

public class delegate
{

    private int delegateId ;
    private int number ;
    private String name ;
    private String imageUrl;
    private int mainArea;

    public delegate( ) {

    }


    public delegate( int number, String name, String imageUrl, int mainArea) {
        this.number = number;
        this.name = name;
        this.imageUrl = imageUrl;
        this.mainArea = mainArea;
    }



    public int getDelegateId() {
        return delegateId;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getMainArea() {
        return mainArea;
    }



    public void setDelegateId(int delegateId) {
        this.delegateId = delegateId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMainArea(int mainArea) {
        this.mainArea = mainArea;
    }
}
