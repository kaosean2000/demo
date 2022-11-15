package com.example.demo.performaceTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class GetClassroomUsagePostTask extends PostTask{
    public GetClassroomUsagePostTask(PerformanceTestManager manager) {
        super(manager);
    }
    @Override
    public RequestBuilder getRequest() {
        return MockMvcRequestBuilders.post("/getClassroomUsage")
                .param("_day", getRandomDay())
                .param("_time", getRandomScheduleRange())
                .param("_classroom", getRandomClassroom())
                .param("_page",getRandomPage())
                .param("_limit","100")
                .accept(MediaType.APPLICATION_JSON);
    }
}
