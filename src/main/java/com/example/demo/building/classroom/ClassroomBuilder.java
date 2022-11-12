package com.example.demo.building.classroom;


import com.example.demo.building.IBuilding;

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
    public final ClassroomBuilder addProperty(String ...ss) {
        for (var s : ss) {
            rooms.forEach(e -> {
                e.addProperty(s);
            });
        }
        return this;
    }

    public BuildingBuilder finish() {
        return prevBuilder;
    }

}
