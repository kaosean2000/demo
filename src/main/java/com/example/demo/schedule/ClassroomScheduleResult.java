package com.example.demo.schedule;

import com.example.demo.course.Course;
import lombok.Data;

@Data
public class ClassroomScheduleResult {
    public Schedule schedule;
    public Course course;

    public ClassroomScheduleResult(Course course, Schedule schedule) {
        this.course = course;
        this.schedule = schedule;
    }
}
