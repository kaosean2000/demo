package com.example.demo;

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
                        .param("_time","D6-E2")
                        .param("_day","3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void f1(){
//        var d = manager.getContainsRooms("LE4A","LE403","ES");
//        Assertions.assertEquals(d.toString(), "[ES217, ES317, ES417, ES418, ES517, ES509, ES301, ES302, ES401, ES101, ES107, ES305, ES404, ES108, ES306, ES405, ES504, ES105, ES501, ES106, ES304, ES309, ES408, ES507, ES409, ES508, ES307, ES406, ES505, ES308, ES407, ES506]");

        var d1 = buildingService.getRoom("ES301").get();
        Assertions.assertEquals(d1.isValidForFreeUse(Schedule.E1,1),false);

        var d2 = buildingService.getRoom("ES306").get();
        Assertions.assertEquals(d2.isValidForFreeUse(Schedule.E1,1),true);
        Assertions.assertEquals(d2.canSpeak(),true);

//        var d3 = manager.getRoom("LE401B").get();
//        Assertions.assertEquals(d3.isValidForFreeUse(Schedule.E1,1),true);
//        Assertions.assertEquals(d3.canSpeak(),false);


    }
    @Test
    public void test1() {
        long time = System.currentTimeMillis();
        for(int j=0;j<15;j++){
            try {
                f("/getFreeClassroom?_time=D0-E4&_day=1");
                f("/getFreeClassroom?_time=D0-E4&_day=5");
                f("/getClassroomUsage?_day=2");
                f("/getClassroomUsage?_day=1");
                f("/getClassroomUsage?_day=4");
                f("/getClassroomUsage?_day=6");
                f("/getClassroomUsage?_day=3");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(System.currentTimeMillis() - time);
    }

    public void f(String url1) throws Exception {
        var result = mvc.perform(MockMvcRequestBuilders.post(url1).accept(MediaType.APPLICATION_JSON)).andReturn();
    }
}
