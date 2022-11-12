package com.example.demo.controller;

import com.example.demo.dto.GetFreeClassroomDTO;
import com.example.demo.service.GetFreeClassroomService;
import com.example.demo.util.ResultLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetFreeClassroomController {
    @Autowired
    GetFreeClassroomService getFreeClassroomService;

    @PostMapping(value = "/getFreeClassroom",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> getFreeClassroom(String[] _classroom,
                                  @RequestParam(defaultValue = "E1-E2") String _time,
                                  @RequestParam(defaultValue = "1") int _day,
                                  @RequestParam(defaultValue = "0") int _limit,
                                  @RequestParam(defaultValue = "0") int _page){

        var dto = new GetFreeClassroomDTO(_classroom,_day+"",_time);
        if(_limit!=0)
            dto.setLimit(new ResultLimiter(_limit,_page));
        return getFreeClassroomService.getFree(dto);
    }
}
