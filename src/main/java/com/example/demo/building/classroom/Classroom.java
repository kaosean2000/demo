package com.example.demo.building.classroom;

import com.example.demo.schedule.Schedule;
import com.example.demo.building.BuildingEnum;
import com.example.demo.building.IBuilding;
import com.example.demo.building.classroom.property.*;
import com.example.demo.dto.ClassroomDatabaseDTO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Classroom implements IClassroom ,Comparable {
    List<IClassroomProperty> propertyList = new ArrayList<>();
    int floor;
    String no;
    IBuilding building;
    String info,latitude,longitude;
    Classroom(IBuilding building,int floor,String no){
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
            addProperty(prop);

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
        return !isSelfLearnRoom() && isComputerRoom();
    }

    @Override
    public boolean isComputerRoom() {
        return hasProperty(ComputerCP.class);
    }

    @Override
    public boolean isSelfLearnRoom() {
        return hasProperty(SelfLearnCP.class);
    }

    @Override
    public boolean isValid() {
        return !(hasProperty(OfficeCP.class) || hasProperty(RepairCP.class));
    }

    @Override
    public boolean isValidForFreeUse(Schedule time,int day) {
        return isValid() && !(isPlayground() || isAutoLock())  && isOpen(time,day);
    }

    @Override
    public boolean canSpeak() {
        return !hasProperty(SelfLearnCP.class);
    }

    @Override
    public boolean isPlayground() {
        return hasProperty(PlaygroundCP.class);
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
//            if(this.getBuildingEnum()!=b.getBuildingEnum())
//                return this.getBuildingEnum().ordinal()-b.getBuildingEnum().ordinal();
//            if(this.floor != b.floor)
//                return this.floor-b.floor;
//
//            return this.no.compareTo(b.no);
            return this.getName().compareTo(b.getName());
        }
        return 0;
    }
}
