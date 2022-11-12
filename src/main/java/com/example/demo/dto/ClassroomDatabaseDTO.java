package com.example.demo.dto;


import com.example.demo.building.BuildingManager;
import com.example.demo.building.IBuilding;

import java.util.List;
import java.util.Map;

public class ClassroomDatabaseDTO {
    private final String building,no,info,latitude,longitude,property,class_name;
    private final int floor;
    private BuildingManager manager;

    public IBuilding getBuilding() {
        return manager.getBuilding(building);
    }

    public String getNo() {
        return no;
    }

    public String getInfo() {
        return info;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getFloor() {
        return floor;
    }

    public ClassroomDatabaseDTO(String building, int floor, String no, String info,String property, String latitude, String longitude,String class_name) {
        this.building = building;
        this.no = no;
        this.info = info;
        this.latitude = latitude;
        this.longitude = longitude;
        this.floor = floor;
        this.property = property;
        this.class_name = class_name;
    }
    public ClassroomDatabaseDTO(BuildingManager manager,Map<String, Object> data){
        this(
                (String) data.get("building"),
                Integer.parseInt((String) data.get("floor")),
                (String)  data.get("no"),
                (String)  data.get("info"),
                (String)  data.get("property"),
                (String)  data.get("latitude"),
                (String)  data.get("longitude"),
                (String)  data.get("class_name")
        );
        this.manager = manager;
    }

    public List<String> getPropertyList(){
        if(property == null || property.isEmpty())
            return List.of();
        var propertys = property.split(",");
        return List.of(propertys);
    }

    public String getClassName() {
        return class_name;
    }
}
