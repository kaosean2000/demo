package service;

import data.Classroom;

import java.util.List;

public interface LoadClassroomDataDAO {
    void loadData();
    List<Classroom> findByBuilding(String name);
}
