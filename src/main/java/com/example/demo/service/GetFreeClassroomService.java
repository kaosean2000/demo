package com.example.demo.service;

import com.example.demo.schedule.ClassroomScheduleInspector;
import com.example.demo.DemoApplication;
import com.example.demo.dto.GetFreeClassroomDTO;
import com.example.demo.util.ResultLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFreeClassroomService {
    @Autowired
    DatabaseInterfaceService databaseService;
    public List<String> getFree(GetFreeClassroomDTO dto) {
        ClassroomScheduleInspector manager = new ClassroomScheduleInspector();
        var room = dto.getClassrooms();
        manager.addClassroom(DemoApplication.defaultBuildingManager.getContainsRooms(room));
        var dataList = databaseService.loadDataBySemesterAndDay(dto.getSemester(),dto.getDay());
        manager.loadCourseData(dataList);
        var result = manager.getFreeClassroomList(
                Integer.parseInt(dto.getDay()), dto.getStartTime(),dto.getEndTime());
        return ResultLimiter.getLimitedList(dto.getLimit(),result);
    }
}
