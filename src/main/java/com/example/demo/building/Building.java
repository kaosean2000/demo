package com.example.demo.building;

import com.example.demo.building.classroom.Classroom;

import java.util.ArrayList;
import java.util.List;

public class Building implements IBuilding {
    BuildingEnum buildingEnum;
    List<Classroom> classroomList = new ArrayList<>();
    public Building( BuildingEnum buildingEnum){
        this.buildingEnum = buildingEnum;
    }
    @Override
    public BuildingEnum getBuildingEnum() {
        return buildingEnum;
    }

    @Override
    public void addClassroom(Classroom c) {
        classroomList.add(c);
    }

    @Override
    public String getName() {
        return buildingEnum.symbolName;
    }
}
