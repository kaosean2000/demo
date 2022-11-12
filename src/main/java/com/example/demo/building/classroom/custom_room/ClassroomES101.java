package com.example.demo.building.classroom.custom_room;

import com.example.demo.building.classroom.Classroom;
import com.example.demo.dto.ClassroomDatabaseDTO;
import com.example.demo.schedule.Schedule;

import java.time.temporal.ValueRange;

public class ClassroomES101 extends Classroom {
    public ClassroomES101(ClassroomDatabaseDTO dto) {
        super(dto);
    }

    @Override
    public boolean isOpen(Schedule time, int day) {
        if(!ValueRange.of(1, 5).isValidIntValue(day))
            return false;
        return time.isInRange(Schedule.D7,Schedule.E3);
    }
}
