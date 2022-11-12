package com.example.demo.service;

import com.example.demo.dao.DatabaseDAO;
import com.example.demo.vo.DBResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class DatabaseInterfaceService {
    @Autowired
    DatabaseDAO databaseDAO;
    @Cacheable(value = "database_cache")
    public DBResultVO loadDataBySemesterAndDay(String semester, String day) {
        return databaseDAO.query(
                "select 教室,節次,開課單位,科目名稱_中,授課教師,課程碼 from test.Course where 學期 = '"+semester+"' and 星期 = '" + day + "';");
    }

    public DBResultVO loadClassroomFromCourse() {
        return databaseDAO.query(
                "select 教室 as classroom from test.Course group by 教室 order by 教室");
    }
    public DBResultVO loadClassroomFromRoom() {
        return databaseDAO.query(
                "select * from test.Room");
    }
}
