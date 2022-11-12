package com.example.demo.dto;

import com.example.demo.DemoApplication;
import com.example.demo.util.ResultLimiter;

public class ClassroomUsageDTO {
    private final String day;

    public ResultLimiter getLimit() {
        return limit;
    }

    private ResultLimiter limit = null;

    public String[] getClassrooms() {
        return classrooms;
    }

    private final String[] classrooms;

    public String getSemester() {
        return semester;
    }

    public void setLimit(ResultLimiter limit) {
        this.limit = limit;
    }

    private final String semester;

    public String getDay() {
        return day;
    }

    public ClassroomUsageDTO(String[] classrooms, String day) {
        this.classrooms = classrooms;
        this.day = day;
        this.semester = DemoApplication.SEMESTER;
    }
}
