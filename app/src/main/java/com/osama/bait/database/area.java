package com.osama.bait.database;

public class area
{


    private int areaId;
    private String areaName;

    public area() {

    }
    public area( String areaName) {
        this.areaName = areaName;
    }
    public area(int areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }



    public int getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }



}
