package com.example.demo.building;

import com.example.demo.building.BuildingEnum;
import com.example.demo.building.classroom.Classroom;

public interface IBuilding {
    BuildingEnum getBuildingEnum();
    void addClassroom(Classroom c);
    String getName();
}
