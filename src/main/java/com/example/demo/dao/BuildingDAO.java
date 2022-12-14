package com.example.demo.dao;

import com.example.demo.building.Building;
import com.example.demo.building.BuildingEnum;
import com.example.demo.building.IBuilding;
import com.example.demo.building.classroom.IClassroom;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BuildingDAO {
    private final List<BuildingEnum> buildings = new ArrayList<>();
    private final List<IBuilding> buildingList = new ArrayList<>();
    private final Map<String, IClassroom> roomMap = new HashMap<>();
    public BuildingDAO(){
        Collections.addAll(buildings, BuildingEnum.values());
    }
    public void registryBuilding(IBuilding b){
        buildingList.add(b);
    }
    public void registryRoom(){
        buildingList.forEach(this::registryRoom);
    }
    public void registryRoom(IBuilding b){
        if(b!=null)
            b.getClassrooms().forEach(e->roomMap.put(e.getName(),e));
    }
    public void registryRoom(IClassroom b){
        roomMap.put(b.getName(),b);
    }
    public void registryRoom(String s){
        roomMap.put(s,null);
    }
    public Optional<IClassroom> getRoom(String name){
        return Optional.ofNullable(roomMap.get(name));
    }

    public List<String> getContainsRooms(String ...strings){
        Set<String> set = new HashSet<>();
        if(strings==null || strings.length==0)
            strings = new String[]{""};
        for(var s1:strings){
            var list = roomMap.keySet().stream().filter(e->e.contains(s1)).toList();
            set.addAll(list);
        }
        return set.stream().toList();
    }

    public IBuilding getBuilding(String building) {
        var buildingObj = buildingList.stream().
                filter(e->e.getBuildingEnum().name().equals(building)).findFirst();
        if(buildingObj.isPresent())
            return buildingObj.get();
        IBuilding b = new Building(BuildingEnum.valueOf(building));
        registryBuilding(b);
        return b;
    }
}
