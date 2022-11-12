package com.example.demo.building.classroom;

import com.example.demo.building.BuildingEnum;
import com.example.demo.building.IBuilding;
import com.example.demo.schedule.Schedule;

public interface IClassroom {

    String getName();
    IBuilding getBuilding();
    BuildingEnum getBuildingEnum();
    void addProperty(String property);
    boolean hasProperty(String property);
    boolean isAutoLock();
    boolean isComputerRoom();
    boolean isSelfLearnRoom();
    boolean isValid();
    boolean isValidForFreeUse(Schedule time,int day);
    boolean canSpeak();
    boolean isPlayground();
    boolean isOpen(Schedule time,int day);

}
