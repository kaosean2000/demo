package com.example.demo.building.classroom;

import com.example.demo.building.Building;
import com.example.demo.building.BuildingEnum;
import com.example.demo.building.classroom.property.*;

import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.List;

public class Classroom implements IClassroom{
    List<IClassroomProperty> propertyList = new ArrayList<>();
    int floor;
    String no;
    Building building;
    Classroom(Building building,int floor,String no){
        this.building = building;
        this.floor = floor;
        this.no = no;
    }

    @Override
    public String getName() {
        return building.getName()+floor+no;
    }

    @Override
    public Building getBuilding() {
        return building;
    }

    @Override
    public BuildingEnum getBuildingEnum() {
        return getBuilding().getBuildingEnum();
    }

    @Override
    public void addProperty(IClassroomProperty property) {
        if(!hasProperty(property.getClass()))
            propertyList.add(property);
    }

    @Override
    public boolean hasProperty(Class<? extends IClassroomProperty> clazz) {
        return propertyList.stream().anyMatch(clazz::isInstance);
    }

    @Override
    public <T extends IClassroomProperty> T getProperty(Class<? extends T> clazz) {
        var p =  propertyList.stream().filter(clazz::isInstance).findFirst();
        if(p.isPresent())
            return null;
        return (T)p.get();
    }

    @Override
    public boolean isAutoLock() {
        return !hasProperty(SelfLearnerCP.class) && hasProperty(ComputerCP.class);
    }

    @Override
    public boolean isValid() {
        return !(hasProperty(OfficeCP.class) || hasProperty(RepairCP.class));
    }

    @Override
    public boolean isValidForFreeUse() {
        return !(isValid() || isPlayground() || isAutoLock());
    }

    @Override
    public boolean canSpeak() {
        return !hasProperty(SelfLearnerCP.class);
    }

    @Override
    public boolean isPlayground() {
        return hasProperty(PlaygroundCP.class);
    }

}
