package com.example.demo.performaceTest;

import com.example.demo.schedule.Schedule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public abstract class PostTask  implements Runnable {
    private final MockMvc mvc;
    protected final Random rand = new Random();
    PerformanceTestManager manager;

    public PostTask(PerformanceTestManager manager) {
        this.manager = manager;
        this.mvc = manager.getMVC();
        rand.setSeed(System.currentTimeMillis() ^ this.hashCode());
    }

    @Override
    public void run() {
        try {
            var request =getRequest();
            var result = mvc.perform(request).andReturn();
//            printResult(result);
            manager.done();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
    public abstract RequestBuilder getRequest();
    private void printResult(MvcResult result){
        var buffer = result.getResponse().getContentAsByteArray();
        String str = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(buffer)).toString();
        System.out.println(str.substring(0,30));
    }

    protected String getRandomDay() {
        int day = rand.nextInt(5) + 1;
        return day + "";
    }

    protected String getRandomScheduleRange() {
        var startTime = List.of(
                Schedule.E0,
                Schedule.D6,
                Schedule.E2,
                Schedule.D5,
                Schedule.D4,
                Schedule.D0
        );
        var endTime = List.of(
                Schedule.E2,
                Schedule.E1,
                Schedule.E4,
                Schedule.D7,
                Schedule.D8,
                Schedule.E4
        );
        int index = rand.nextInt(startTime.size());
        return startTime.get(index) + "_" + endTime.get(index);
    }

    protected String getRandomClassroom() {
        var list = List.of(
                "ES", "", "SF", "", "", "LE", "", "LA", "", "", "", "", "", "", "", "", ""
        );
        int index = rand.nextInt(list.size());
        return list.get(index);
    }

    protected String getRandomPage() {
        return "0";//rand.nextInt(2)+"";
    }

}