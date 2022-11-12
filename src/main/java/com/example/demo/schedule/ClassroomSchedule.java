package com.example.demo.schedule;

import com.example.demo.DemoApplication;
import com.example.demo.course.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClassroomSchedule {
    public String name;
    ClassroomSchedule(String name){
        this.name = name;
    }
    private final Course[] time = new Course[Schedule.values().length];

    public void addCourse(String s1, String s2, Course course) {
        int _start = Schedule.valueOf(s1).ordinal();
        int _end = Schedule.valueOf(s2).ordinal();
        for(int i=_start;i<=_end;i++)
            time[i] = course;
    }

    public void reset(){
        Arrays.fill(time,null);
    }

    public boolean isFree(int day,String start,String end){
        var room = DemoApplication.defaultBuildingManager.getRoom(name);
        int _start = Schedule.valueOf(start).ordinal();
        int _end   = Schedule.valueOf(end).ordinal();
        for(int i=_start;i<=_end;i++){
            if(time[i] != null)
                return false;
            if(room.isPresent()&&!room.get().isValidForFreeUse(Schedule.values()[i],day))
                return false;
        }
        return true;
    }

    public ClassroomScheduleResult[] getResult() {
        List<ClassroomScheduleResult> list = new ArrayList<>();
        for (int i = 0; i < time.length; i++) {
            if (Schedule.values()[i].isInterSchedule())  continue;
            var result = new ClassroomScheduleResult(time[i],Schedule.values()[i]);
            list.add(result);
        }
        return list.toArray(ClassroomScheduleResult[]::new);
    }

    public String getResult(int i) {
        return Objects.isNull(time[i])?"空教室!":time[i].toString();
    }


}
