package com.example.demo.course;

import lombok.Data;

import java.util.Map;

@Data
public class Course {
   public static final Course EMPTY = new Course(null);
    Map<String,Object> data;

    public Course(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
