package com.example.demo.schedule;

import java.time.temporal.ValueRange;

public enum Schedule {
    D0("D0", "07:10", "08:00"),
    D0_D1("D0_D1", "08:00", "08:10"),
    D1("D1", "08:10", "09:00"),
    D1_D2("D1_D2", "09:00", "09:10"),
    D2("D2", "09:10", "10:00"),
    D2_D3("D2_D3", "10:00", "10:10"),
    D3("D3", "10:10", "11:00"),
    D3_D4("D3_D4", "11:00", "11:10"),
    D4("D4", "11:10", "12:00"),
    D4_DN("D4_DN", "12:00", "12:40"),
    DN("DN", "12:40", "13:30"),
    DN_D5("DN_D5", "13:30", "13:40"),
    D5("D5", "13:40", "14:30"),
    D5_D6("D5_D6", "14:30", "14:40"),
    D6("D6", "14:40", "15:30"),
    D6_D7("D6_D7", "15:30", "15:40"),
    D7("D7", "15:40", "16:30"),
    D7_D8("D7_D8", "16:30", "16:40"),
    D8("D8", "16:40", "17:30"),
    D8_E0("D8_E0", "17:30", "17:40"),
    E0("E0", "17:40", "18:30"),
    E0_E1("E0_E1", "18:30", "18:40"),
    E1("E1", "18:40", "19:30"),
    E1_E2("E1_E2", "19:30", "19:35"),
    E2("E2", "19:35", "20:20"),
    E2_E3("E2_E3", "20:20", "20:30"),
    E3("E3", "20:30", "21:20"),
    E3_E4("E3_E4", "21:20", "21:25"),
    E4("E4", "21:25", "22:10");
    public final String name;
    public final String startTime;
    public final String endTime;

    Schedule(String name, String startTime, String endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public boolean isInterSchedule(){
        return name.length() == 5;
    }
    public boolean isInRange(Schedule start,Schedule end){
        int _start = start.ordinal();
        int index = this.ordinal();
        int _end = end.ordinal();
        return ValueRange.of(_start,_end).isValidIntValue(index);
    }

}
