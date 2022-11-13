package com.example.demo.controller;

import com.example.demo.util.ResultLimiter;
import com.example.demo.schedule.ClassroomSchedule;
import com.example.demo.dto.ClassroomUsageDTO;
import com.example.demo.service.GetClassroomUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetClassroomUsageController {

    @Autowired
    GetClassroomUsageService getClassroomUsageService;

    @PostMapping(value = "/getClassroomUsage",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClassroomSchedule> getClassroomUsage(String[] _classroom,
                                              @RequestParam(defaultValue = "1") int _day,
                                              @RequestParam(defaultValue = "0") int _limit,
                                              @RequestParam(defaultValue = "0") int _page) {

        var dto = new ClassroomUsageDTO(_classroom,_day+"");
        dto.setLimit(ResultLimiter.create(_limit,_page));
        return getClassroomUsageService.getClassroomUsage(dto);
    }

}
