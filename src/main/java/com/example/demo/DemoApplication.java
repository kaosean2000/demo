package com.example.demo;

import com.sun.xml.messaging.saaj.util.ByteOutputStream;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }
}

@Data
class D {
    int id;
    String title;
}

interface DDao {
    List<D> getList(int from, int limit);
    List<Map<String, Object>> query(String s);
    ClassroomSchedule[] getClassroomUsage(String[] classroom,String[] _building, String s);

    List<String>  getFree(String start, String end, String s);
}

@Repository("DBDao")
class DBDataAccessService implements DDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    List<D> dList = new ArrayList<>();

    DBDataAccessService() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setUsername("root");
        source.setPassword("");
        jdbcTemplate = new JdbcTemplate(source);
        getClassroomUsage(null,null,"D0-E4");
    }
    public List<Map<String, Object>> query(String s) {
        Objects.requireNonNull(this.jdbcTemplate,"test");
        return this.jdbcTemplate.queryForList(s);
    }
    @Override
    public List<D> getList(int from, int limit) {
        var list = new ArrayList<D>();
        for (int i = from; i < from + limit && i < dList.size(); i++)
            list.add(dList.get(i));
        return list;
    }

    @Override
    public ClassroomSchedule[] getClassroomUsage(String[] cl,String[] _building, String s) {
        long startTime = System.currentTimeMillis();
        ClassroomScheduleManager manager = new ClassroomScheduleManager();
        if((_building==null||_building.length==0)&&(cl==null||cl.length==0)){
            manager.add(ClassroomSchedule.ES_ROOM);
            manager.add(ClassroomSchedule.LE_ROOM);
        } else{
            if(cl!=null)
                Arrays.stream(cl).forEach(manager::add);
            if(_building!=null) {
                Arrays.stream(_building).forEach(e -> {
                    if (e.equals("ES"))
                        manager.add(ClassroomSchedule.ES_ROOM);
                    else if (e.equals("LE"))
                        manager.add(ClassroomSchedule.LE_ROOM);
                });
            }
        }
        manager.loadData(this,s);
        var result = manager.getResultString();
        System.out.println(System.currentTimeMillis()-startTime);
        return result;
    }

    @Override
    public List<String> getFree(String start, String end, String s) {
        long startTime = System.currentTimeMillis();
        ClassroomScheduleManager manager = new ClassroomScheduleManager();
        manager.add(ClassroomSchedule.ES_ROOM);
        manager.add(ClassroomSchedule.LE_ROOM);
        manager.loadData(this,s);
        var result = manager.getFree(start,end);
        System.out.println(System.currentTimeMillis()-startTime);
        return result;
    }
}

@Service
class S {
    final DDao dService;

    S(@Qualifier("DBDao") DDao d) {
        this.dService = d;
    }

    public int test() {
        return 200;
    }

    public ClassroomSchedule[] getClassroomUsage(String[] classroom,String[] _building, int day) {
        return dService.getClassroomUsage(classroom,_building,day+"");
    }

    public List<String> getFree(String start, String end, int day) {
        return dService.getFree(start,end,day+"");
    }
}

@RequestMapping("/hello")
@RestController
class A {
    @Autowired
    S s;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<D> hello(@RequestParam(defaultValue = "30") String _limit, @RequestParam(defaultValue = "0") String _page) {
        return null;
    }
}
@RequestMapping("/getClassroomUsage")
@RestController
class A1 {
    @Autowired
    S s;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ClassroomSchedule[] hello(String[] _classroom,String[] _building, @RequestParam(defaultValue = "1") int _day) {
        return s.getClassroomUsage(_classroom,_building, _day);
    }
}

@RequestMapping("/getFree")
@RestController
class A2 {
    @Autowired
    S s;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<String>  hello(@RequestParam(defaultValue = "D7-E0") String _time, @RequestParam(defaultValue = "1") String _day) {
        return s.getFree(_time.substring(0,2),_time.substring(3,5), Integer.parseInt(_day));
    }
}

@RestController
class A3 {
    @Autowired
    S s;

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    String  hello(HttpServletRequest request) throws ServletException {
        //request.login(request.getParameter("u"),request.getParameter("s"));
        request.getSession().setAttribute("key","123");
        System.out.println(request.getSession().getMaxInactiveInterval());
        return "123";
    }
    @GetMapping(value = "/test1", produces = MediaType.APPLICATION_JSON_VALUE)
    Object  hello1(HttpServletRequest request) {
        System.out.println(System.currentTimeMillis()-request.getSession().getCreationTime());
        return request.getSession().getAttribute("key");
    }
    @GetMapping(value = "/test2", produces = MediaType.APPLICATION_JSON_VALUE)
    Object  hello2(HttpServletRequest request) throws ServletException {
        request.logout();
        return  "123";
    }
    @GetMapping(value = "/image",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage() throws IOException {
        BufferedImage bImg = new BufferedImage(800,800,BufferedImage.TYPE_3BYTE_BGR);
        var g = bImg.createGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.setColor(Color.ORANGE);
        g.fillOval(0,0,300,300);
        g.setColor(Color.black);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.4F);
        g.setFont(newFont);
        g.drawString("ES306",100,100);

        ByteOutputStream s = new ByteOutputStream();
        ImageIO.write(bImg, "png", s);
        s.flush();
        return s.getBytes();
    }
}