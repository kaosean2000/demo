package com.example.demo.building.classroom;

import com.example.demo.DemoApplication;
import com.example.demo.building.*;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class BuildingBuilder {
    IBuilding building;
    public BuildingBuilder( BuildingEnum buildingEnum){
        building = new Building(buildingEnum);
    }
    public ClassroomBuilder addRoom(int floor, String ...no){
        return new ClassroomBuilder(this,floor,no);
    }

    public ClassroomBuilder addRoomInRange(int floor, String start,String end){
        int _start = Integer.parseInt(start);
        int _end = Integer.parseInt(end);
        List<String> s = new ArrayList<>();
        for(int i = _start;i<=_end;i++)
            s.add(i+"");
        String[] ss = s.stream().map(e->e.length()==1?"0"+e:e).toArray(String[]::new);
        return new ClassroomBuilder(this,floor,ss);
    }
    public IBuilding build(BuildingManager manager){
        if(manager!=null){
            manager.registryBuilding(building);
            manager.registryRoom();
            return building;
        }
        return build();
    }
    public IBuilding build(){
        return building;
    }
}
