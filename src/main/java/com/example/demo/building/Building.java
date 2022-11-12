package com.example.demo.building;

import com.example.demo.building.classroom.Classroom;
import com.example.demo.building.classroom.IClassroom;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Building implements IBuilding{
    BuildingEnum buildingEnum;
    List<IClassroom> classroomList = new ArrayList<>();
    public Building( BuildingEnum buildingEnum){
        this.buildingEnum = buildingEnum;
    }
    @Override
    public BuildingEnum getBuildingEnum() {
        return buildingEnum;
    }

    @Override
    public void addClassroom(IClassroom c) {
        classroomList.add(c);
    }

    @Override
    public List<IClassroom> getClassrooms() {
        return classroomList;
    }
    @Override
    public Optional<IClassroom> getClassroom(String s) {
        return classroomList.stream().filter(e->e.getName().equals(s)).findFirst();
    }

    @Override
    public String getName() {
        return buildingEnum.symbolName;
    }
    @Override
    public String toString(){
        return getName();
    }


}
