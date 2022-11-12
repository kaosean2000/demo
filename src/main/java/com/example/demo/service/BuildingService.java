package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.building.BuildingManager;
import com.example.demo.dao.ILoadBuildingDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    ILoadBuildingDAO dao;
    BuildingManager manager = DemoApplication.defaultBuildingManager;
    public BuildingService(@Qualifier("DB") ILoadBuildingDAO dao){
        this.dao=dao;
        dao.loadData(manager);

    }
    public void setLoadBuildingDAO(ILoadBuildingDAO dao){
        this.dao = dao;
    }
}
