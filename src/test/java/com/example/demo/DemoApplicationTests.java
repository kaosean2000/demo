package com.example.demo;

import com.example.demo.performaceTest.GetClassroomUsagePostTask;
import com.example.demo.performaceTest.GetFreeClassroomPostTask;
import com.example.demo.performaceTest.GetRunnableInClassList;
import com.example.demo.performaceTest.PerformanceTestManager;
import com.example.demo.schedule.Schedule;
import com.example.demo.service.BuildingService;
import com.example.demo.service.GetClassroomUsageService;
import com.example.demo.service.GetFreeClassroomService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.TimeUnit;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class DemoApplicationTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    GetFreeClassroomService getFreeClassroomService;
    @Autowired
    GetClassroomUsageService getClassroomUsageService;
    @Autowired
    BuildingService buildingService;

    @Test
    public void f2() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders
                                .post("/getFreeClassroom")
                                .param("_time", "D6-E2")
                                .param("_day", "3")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void f1() {
        var d1 = buildingService.getRoom("ES301").get();
        Assertions.assertEquals(d1.isValidForFreeUse(Schedule.E1, 1), false);

        var d2 = buildingService.getRoom("ES306").get();
        Assertions.assertEquals(d2.isValidForFreeUse(Schedule.E1, 1), true);
        Assertions.assertEquals(d2.canSpeak(), true);

//        var d3 = manager.getRoom("LE401B").get();
//        Assertions.assertEquals(d3.isValidForFreeUse(Schedule.E1,1),true);
//        Assertions.assertEquals(d3.canSpeak(),false);


    }

    @Test
    public void performanceTest() throws InterruptedException {
        GetRunnableInClassList o = new GetRunnableInClassList(
                GetClassroomUsagePostTask.class,
                GetFreeClassroomPostTask.class
        );
        var obj = new PerformanceTestManager(mvc,4, TimeUnit.SECONDS,100000,o);
        obj.startTest();
    }

}
