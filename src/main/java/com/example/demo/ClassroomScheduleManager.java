package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ClassroomScheduleManager {

    List<ClassroomSchedule> list = new ArrayList<>();

    public void add(List<String> s) {
        s.forEach(this::add);
    }

    public void add(String s) {
        add(new ClassroomSchedule(s));
    }

    public void add(ClassroomSchedule c) {
        if(list.stream().anyMatch(e -> e.name.equals(c.name)))
            return;
        list.add(c);
    }

    public void loadData(DDao dao, String day) {
        var list1 = dao.query(
                "select 教室,節次,開課單位,科目名稱_中 from test.A where 學期 = '1101' and 星期 like '%" + day + "%';");
        list.forEach(ClassroomSchedule::reset);
        list1.forEach(e -> {
            String classroom = e.get("教室").toString();
            String time = e.get("節次").toString();
            String name = e.get("開課單位").toString() + " " + e.get("科目名稱_中").toString();
            String start = time.substring(0, 2);
            String end = time.substring(3);
            list.stream().filter(e1 -> e1.name.equals(classroom))
                    .forEach(e2 -> e2.addClass(start, end, name));
        });
    }

    public ClassroomSchedule[] getResultString() {
        return list.toArray(ClassroomSchedule[]::new);
    }

    public List<String> getFree(String start, String end) {
        return list.stream()
                .filter(e -> e.isFree(start, end))
                .map(e -> e.name)
                .toList();
    }
}
