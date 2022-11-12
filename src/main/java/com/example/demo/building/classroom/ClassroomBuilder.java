package com.example.demo.building.classroom;


import com.example.demo.building.Building;
import com.example.demo.building.IBuilding;
import com.example.demo.building.classroom.property.IClassroomProperty;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ClassroomBuilder {
    BuildingBuilder prevBuilder;
    IBuilding building;
    List<Classroom> rooms = new ArrayList<>();

    public ClassroomBuilder(IBuilding building, int floor, String no) {
        this.building = building;
        rooms.add(new Classroom(building, floor, no));
    }

    public ClassroomBuilder(BuildingBuilder prevBuilder, int floor, String... noArray) {
        this.prevBuilder = prevBuilder;
        this.building = prevBuilder.building;
        for (var no : noArray) {
            var room = new Classroom(building, floor, no);
            building.addClassroom(room);
            rooms.add(room);
        }
    }

    @SafeVarargs
    public final ClassroomBuilder addProperty(Class<? extends IClassroomProperty>... clazz) {
        for (var c : clazz) {
            rooms.forEach(e -> {
                try {
                    e.addProperty(c.newInstance());
                } catch (InstantiationException | IllegalAccessException e1) {
                    throw new RuntimeException(e1);
                }
            });
        }
        return this;
    }

    public BuildingBuilder finish() {
        return prevBuilder;
    }

}
