package com.example.demo;

import com.example.demo.building.BuildingUtils;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;


@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {
    //@Autowired
    //private WebApplicationContext webApplicationContext;
   // MockMvc mvc;

    @Autowired
    S s;
    @Autowired
    DBDataAccessService dao;
    @Before
    public void setup() {
       // mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     *把資料庫裡所有的教室轉換成大樓代號後，檢查室否有找不到的大樓代號。
     */
    @Test
    void buildingNotFoundTest() {
        var list = dao.query("select 教室 from test.A group by 教室;");
        var list1=
                list.stream()
                .map(e->e.get("教室"))
                .filter(e->!BuildingUtils.getBuildingFromString(e.toString()))
                .toList();
        Assertions.assertEquals(list1.toString(),"[]");
    }
    @Test
    void test1() {
        long time = System.currentTimeMillis();
        for(int i=0;i<100;i++){

            s.getFree("D0","E4",1);
            s.getFree("D7","E2",5);
            s.getClassroomUsage(null,null,2);
            s.getClassroomUsage(null,null,4);

        }
        System.out.println(System.currentTimeMillis()-time);
    }
}
