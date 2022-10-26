package com.example.demo;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
}

@Repository("fakeDao")
class FakeDDataAccessService implements DDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    List<D> dList = new ArrayList<>();

    FakeDDataAccessService() {
        for (int i = 1; i <= 40; i++) {
            D d = new D();
            d.setTitle(i + ": " + Math.random());
            dList.add(d);
        }

    }

    @Override
    public List<D> getList(int from, int limit) {
        var list = new ArrayList<D>();
        for (int i = from; i < from + limit && i < dList.size(); i++)
            list.add(dList.get(i));
        return list;
    }
}

@Repository("DBDao")
class DBDataAccessService implements DDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    List<D> dList = new ArrayList<>();

    DBDataAccessService() {
//        DriverManagerDataSource source = new DriverManagerDataSource();
//        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        source.setUrl("jdbc:mysql://localhost:3306/mysql");
//        source.setUsername("root");
//        source.setPassword("");
//        jdbcTemplate = new JdbcTemplate(source);
//        var map = this.jdbcTemplate.queryForList("SELECT * FROM A");
//        System.out.println(map);
//        for (int i = 1; i <= 40; i++) {
//            for (int j = 0; j < map.size(); j++) {
//                D d = new D();
//                d.setTitle(i + ": " + Math.random() + map.get(j).get("title"));
//                dList.add(d);
//            }
//        }
    }

    @Override
    public List<D> getList(int from, int limit) {
        var list = new ArrayList<D>();
        for (int i = from; i < from + limit && i < dList.size(); i++)
            list.add(dList.get(i));
        return list;
    }
}

@Service
class S {
    final DDao dService;

    S(@Qualifier("DBDao") DDao d) {
        this.dService = d;

    }

    List<D> getList(int from, int limit) {
        return dService.getList(from, limit);
    }

    public int test() {
        return 200;
    }
}

@RequestMapping("/hello")
@RestController
class A {
    @Autowired
    S s;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<D> hello(@RequestParam String _limit, @RequestParam String _page) {
        int limit = Integer.parseInt(_limit);
        int page = Integer.parseInt(_page);
        int startID = limit * page;
        return s.getList(startID, limit);
    }
}