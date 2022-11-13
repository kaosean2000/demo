package com.example.demo.service;

import com.example.demo.util.ResultLimiter;
import com.example.demo.schedule.ClassroomSchedule;
import com.example.demo.schedule.ClassroomScheduleInspector;
import com.example.demo.DemoApplication;
import com.example.demo.dto.ClassroomUsageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetClassroomUsageService {
    @Autowired
    DatabaseInterfaceService databaseService;
    @Autowired
    BuildingService buildingService;
    public List<ClassroomSchedule> getClassroomUsage(ClassroomUsageDTO dto) {

        ClassroomScheduleInspector manager = new ClassroomScheduleInspector();

        var roomsStr = dto.getClassrooms();
        var rooms = buildingService.getContainsRooms(roomsStr);
        manager.addClassroom(rooms);

        var dbResult = databaseService.loadDataBySemesterAndDay(dto.getSemester(),dto.getDay());
        manager.loadCourseData(dbResult);

        var result = manager.getClassroomScheduleList();
        return ResultLimiter.getLimitedList(dto.getLimit(),result);
    }
}
