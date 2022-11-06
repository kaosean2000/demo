package com.example.demo.building.classroom;

import com.example.demo.building.Building;
import com.example.demo.building.BuildingEnum;
import com.example.demo.building.classroom.property.IClassroomProperty;

public interface IClassroom {

    String getName();
    Building getBuilding();
    BuildingEnum getBuildingEnum();
    void addProperty(IClassroomProperty property);
    boolean hasProperty(Class<? extends IClassroomProperty> clazz);
    <T extends IClassroomProperty> T getProperty(Class<? extends T> clazz);

    boolean isAutoLock();
    boolean isValid();
    boolean isValidForFreeUse();
    boolean canSpeak();
    boolean isPlayground();

}
