package com.example.demo.building.classroom;

import com.example.demo.building.BuildingEnum;
import com.example.demo.building.IBuilding;
import com.example.demo.dto.ClassroomDatabaseDTO;
import com.example.demo.schedule.Schedule;
import org.jetbrains.annotations.NotNull;


public class Classroom implements IClassroom ,Comparable {
    ClassroomProperties properties = new ClassroomProperties();
    int floor;
    String no;
    IBuilding building;
    String info,latitude,longitude;
    public Classroom(IBuilding building, int floor, String no){
        this.building = building;
        this.floor = floor;
        this.no = no;
    }
    public Classroom(ClassroomDatabaseDTO dto){
        this.building = dto.getBuilding();
        this.floor = dto.getFloor();
        this.no = dto.getNo();
        this.info = dto.getInfo();
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        var props = dto.getPropertyList();
        for(var prop:props)
            properties.addProperty(prop);

    }
    @Override
    public String getName() {
        return building.getName()+floor+no;
    }

    @Override
    public IBuilding getBuilding() {
        return building;
    }

    @Override
    public BuildingEnum getBuildingEnum() {
        return getBuilding().getBuildingEnum();
    }

    @Override
    public void addProperty(String property) {
        properties.addProperty(property);
    }

    @Override
    public boolean hasProperty(String property) {
        return properties.hasProperty(property);
    }


    @Override
    public boolean isAutoLock() {
        return properties.isAutoLock();
    }

    @Override
    public boolean isComputerRoom() {
        return properties.isComputerRoom();
    }

    @Override
    public boolean isSelfLearnRoom() {
        return properties.isSelfLearnRoom();
    }

    @Override
    public boolean isValid() {
        return properties.isValid();
    }

    @Override
    public boolean isValidForFreeUse(Schedule time,int day) {
        return properties.isValidForFreeUse() && isOpen(time,day);
    }

    @Override
    public boolean canSpeak() {
        return properties.canSpeak();
    }

    @Override
    public boolean isPlayground() {
        return properties.isPlayground();
    }

    @Override
    public boolean isOpen(Schedule time,int day) {
        return true;
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public int compareTo(@NotNull Object o) {
        if(o instanceof Classroom b){
            return this.getName().compareTo(b.getName());
        }
        return 0;
    }
}
