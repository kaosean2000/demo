package com.example.demo.dto;


import com.example.demo.DemoApplication;
import com.example.demo.util.ResultLimiter;

public class GetFreeClassroomDTO {
    private final String startTime,endTime,day,semester;
    private final String[] classrooms;

    public ResultLimiter getLimit() {
        return limit;
    }

    public void setLimit(ResultLimiter limit) {
        this.limit = limit;
    }

    private ResultLimiter limit;

    public String[] getClassrooms(){
        return classrooms;
    }
    public GetFreeClassroomDTO(String[] classrooms, String day, String time) {
        this.day = day;
        this.startTime = time.substring(0,2);
        this.endTime = time.substring(3,5);
        this.classrooms = classrooms;
        this.semester = DemoApplication.SEMESTER;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSemester() {
        return semester;
    }
}
