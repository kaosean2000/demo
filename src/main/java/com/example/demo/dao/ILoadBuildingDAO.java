package com.example.demo.dao;

import com.example.demo.building.BuildingManager;
import org.springframework.stereotype.Repository;

public interface ILoadBuildingDAO {
    void loadData(BuildingManager manager);
}
