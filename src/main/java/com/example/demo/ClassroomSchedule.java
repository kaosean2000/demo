package com.example.demo;


import com.example.demo.building.BuildingEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClassroomSchedule {
    public static List<String> ES_ROOM =new ArrayList<>();
    public static List<String> LE_ROOM =new ArrayList<>();
    static {
        for (int i = 1; i < 8; i++) {
            for (int j = 3; j <= 9; j++)
                ES_ROOM.add("ES"+i+"0"+j);
        }
        for (int i = 1; i < 6; i++) {
            LE_ROOM.add("LE"+i+"A");
            LE_ROOM.add("LE"+i+"B");
            for (int j = 1; j <= 6; j++)
                LE_ROOM.add("LE"+i+"0"+j);
        }
    }
    public String name;
    ClassroomSchedule(String name){
        this.name = name;
    }
    private final String[] time = new String[Schedule.values().length];

    public void addClass(String s1, String s2, String name) {
        boolean firstHasFound = false;
        for (int i = 0; i < Schedule.values().length; i++) {
            var sch = Schedule.values()[i];
            if (sch.name.equals(s1))
                firstHasFound = true;
            if (firstHasFound)
                time[i] = name;
            if (sch.name.equals(s2))
                break;
        }
    }
    public void reset(){
        Arrays.fill(time,null);
    }

    public String[] getResultString() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < time.length; i++) {
            if (Schedule.values()[i].isInterClass)
                continue;
            if (Objects.isNull(time[i])) {
                list.add(Schedule.values()[i].name+ ": 空教室!");
            } else {
                list.add(Schedule.values()[i].name+ ": "+ time[i]);
            }
        }
        return list.toArray(String[]::new);
    }

    public String getResultString(int i) {
        if (Objects.isNull(time[i]))
            return "空教室!";
        return time[i];
    }
    public boolean isFree(String start,String end){
        boolean firstHasFound = false;
        for (int i = 0; i < Schedule.values().length; i++) {
            var sch = Schedule.values()[i];
            if (sch.name.equals(start))
                firstHasFound = true;
            if (firstHasFound)
                if(time[i]!=null)
                    return false;
            if (sch.name.equals(end))
                break;
        }
        return true;
    }
    BuildingEnum building;

    enum Schedule {
        D0   ("D0",    "07:10", "08:00", false),
        D0_D1("D0_D1", "08:00", "08:10", true),
        D1   ("D1",    "08:10", "09:00", false),
        D1_D2("D1_D2", "09:00", "09:10", true),
        D2   ("D2",    "09:10", "10:00", false),
        D2_D3("D2_D3", "10:00", "10:10", true),
        D3   ("D3",    "10:10", "11:00", false),
        D3_D4("D3_D4", "11:00", "11:10", true),
        D4   ("D4",    "11:10", "12:00", false),
        D4_DN("D4_DN", "12:00", "12:40", true),
        DN   ("DN",    "12:40", "13:30", false),
        DN_D5("DN_D5", "13:30", "13:40", true),
        D5   ("D5",    "13:40", "14:30", false),
        D5_D6("D5_D6", "14:30", "14:40", true),
        D6   ("D6",    "14:40", "15:30", false),
        D6_D7("D6_D7", "15:30", "15:40", true),
        D7   ("D7",    "15:40", "16:30", false),
        D7_D8("D7_D8", "16:30", "16:40", true),
        D8   ("D8",    "16:40", "17:30", false),
        D8_E0("D8_E0", "17:30", "17:40", true),
        E0   ("E0",    "17:40", "18:30", false),
        E0_E1("E0_E1", "18:30", "18:40", true),
        E1   ("E1",    "18:40", "19:30", false),
        E1_E2("E1_E2", "19:30", "19:35", true),
        E2   ("E2",    "19:35", "20:20", false),
        E2_E3("E2_E3", "20:20", "20:30", true),
        E3   ("E3",    "20:30", "21:20", false),
        E3_E4("E3_E4", "21:20", "21:25", true),
        E4   ("E4",    "21:25", "22:10", false);
        public final String name;
        public final String startTime;
        public final String endTime;
        public final boolean isInterClass;

        Schedule(String name, String startTime, String endTime, boolean isInterClass) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
            this.isInterClass = isInterClass;
        }
    }
}
