package com.example.demo.building;

import com.example.demo.building.classroom.IClassroom;

import java.util.*;


@Deprecated
public class BuildingUtils {

    public static Optional<IClassroom> getRoom(BuildingManager manager,String b){
       return manager.getRoom(b);
    }


}
