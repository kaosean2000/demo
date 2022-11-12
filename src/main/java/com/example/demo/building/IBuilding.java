package com.example.demo.building;

import com.example.demo.building.classroom.IClassroom;

import java.util.List;
import java.util.Optional;

public interface IBuilding {
    BuildingEnum getBuildingEnum();
    void addClassroom(IClassroom c);
    Optional<IClassroom> getClassroom(String s);
    List<IClassroom> getClassrooms();
    String getName();
}
