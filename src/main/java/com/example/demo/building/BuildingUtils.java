package com.example.demo.building;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BuildingUtils {
    static List<BuildingEnum> buildings = new ArrayList<>();
    static {
        Collections.addAll(buildings, BuildingEnum.values());
    }


    public static boolean getBuildingFromString(String s){
        String result = "";
        if(s.matches("[A-Z]{2}[0-9]\\w*")){
            result = s.substring(0,2);
        }else if(s.matches("[A-Z]{3}[0-9]\\w*")){
            result = s.substring(0,3);
        }else{
            result = s;
        }
        try {
            getBuildingFromBuildingName(result);
            return true;
        }catch (Exception e){

        }
        return false;
    }
    static BuildingEnum getBuildingFromBuildingName(String s){
        Optional<BuildingEnum> result = buildings.stream().filter(e->e.symbolName.equals(s)).findFirst();
        if(result.isEmpty())
            throw new IllegalArgumentException("");
        return result.get();
    }
}
