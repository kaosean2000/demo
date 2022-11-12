package com.example.demo.building.classroom;

import com.example.demo.schedule.Schedule;
import com.example.demo.building.BuildingEnum;
import com.example.demo.building.IBuilding;
import com.example.demo.building.classroom.property.IClassroomProperty;

public interface IClassroom {

    String getName();
    IBuilding getBuilding();
    BuildingEnum getBuildingEnum();
    void addProperty(IClassroomProperty property);
    boolean hasProperty(Class<? extends IClassroomProperty> clazz);
    <T extends IClassroomProperty> T getProperty(Class<? extends T> clazz);

    boolean isAutoLock();
    boolean isComputerRoom();
    boolean isSelfLearnRoom();
    boolean isValid();
    boolean isValidForFreeUse(Schedule time,int day);
    boolean canSpeak();
    boolean isPlayground();
    boolean isOpen(Schedule time,int day);

}
