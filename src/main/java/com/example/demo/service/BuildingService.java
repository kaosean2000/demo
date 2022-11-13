package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.building.Building;
import com.example.demo.building.BuildingEnum;
import com.example.demo.building.BuildingManager;
import com.example.demo.building.IBuilding;
import com.example.demo.building.classroom.IClassroom;
import com.example.demo.dao.ILoadBuildingDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BuildingService {
    ILoadBuildingDAO dao;
    BuildingManager manager = new BuildingManager();
    public BuildingService(@Qualifier("DB") ILoadBuildingDAO dao){
        this.dao=dao;
        dao.loadData(manager);
    }
//    public BuildingService(BuildingManager manager){
//        this.manager = manager;
//    }
    public BuildingManager getManager(){
        return this.manager;
    }
    public void registryRoom(){
        getManager().registryRoom();
    }
    public void registryRoom(IBuilding b){
       getManager().registryRoom(b);
    }
    public void registryRoom(IClassroom b){
        getManager().registryRoom(b);
    }
    public void registryRoom(String s){
        getManager().registryRoom(s);
    }
    public Optional<IClassroom> getRoom(String name){
        return getManager().getRoom(name);
    }

    public List<String> getContainsRooms(String ...strings){
        return getManager().getContainsRooms(strings);
    }

    public IBuilding getBuilding(String building) {
        return getManager().getBuilding(building);
    }

    public void setManager(BuildingManager manager) {
        this.manager = manager;
    }
}
