package com.example.demo;

import com.example.demo.building.BuildingEnum;
import com.example.demo.building.BuildingManager;
import com.example.demo.building.classroom.BuildingBuilder;
import com.example.demo.service.BuildingService;
import com.example.demo.service.GetClassroomUsageService;
import com.example.demo.service.GetFreeClassroomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class Tests1 {
    @Autowired
    private MockMvc mvc;

    @Autowired
    GetFreeClassroomService getFreeClassroomService;
    @Autowired
    GetClassroomUsageService getClassroomUsageService;
    //@MockBean

    BuildingManager manager = new BuildingManager();
    BuildingService buildingService;

    @Test
    public void f1() throws Exception {
        var SF = new BuildingBuilder(BuildingEnum.SF)
                .addRoom(1,"01").addProperty("ComputerCP").finish()
                .addRoom(1,"02").addProperty("SelfLearnCP","ComputerCP").finish()
                .addRoomInRange(1,"03","08").finish()
                .addRoomInRange(2,"01","15").finish()
                .addRoomInRange(3,"01","15").finish()
                .addRoomInRange(4,"01","15").finish()
                .addRoomInRange(5,"01","15").finish()
                .addRoomInRange(6,"01","15").finish()
                .addRoomInRange(7,"01","15").finish()
                .addRoomInRange(8,"01","15").finish()
                .build();
        manager.registryBuilding(SF);
        manager.registryRoom();
        //Mockito.when(buildingService.getManager()).thenReturn(manager);
        buildingService.setManager(manager);

        System.out.println(buildingService.getManager());


        var result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/getFreeClassroom")
                        .param("_time","D6-E2")
                        .param("_day","3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(result.getRequest().getContentAsString());
    }
}
