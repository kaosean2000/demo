package com.example.demo.schedule;

import com.example.demo.course.Course;
import com.example.demo.service.BuildingService;
import com.example.demo.util.DBResult;

import java.util.ArrayList;
import java.util.List;

public class ClassroomScheduleInspector {

    private final List<ClassroomSchedule> list = new ArrayList<>();

    public void addClassroom(List<String> s) {
        s.forEach(this::addClassroom);
    }

    public void addClassroom(String s) {
        addClassroom(new ClassroomSchedule(s));
    }

    public void addClassroom(ClassroomSchedule c) {
        if(list.stream().anyMatch(e -> e.name.equals(c.name)))
            return;
        list.add(c);
    }

    public void loadCourseData(DBResult result) {
        reset();
        var list1 = result.result();
        for(var e:list1){
            String classroom = e.get("教室").toString();
            String time = e.get("節次").toString();
            String start = time.substring(0, 2);
            String end = time.substring(3);
            var course = new Course(e);
            var _classroom = list.stream()
                    .filter(e1 -> e1.name.equals(classroom))
                    .findAny();
            _classroom.ifPresent(e2 -> e2.addCourse(start, end, course));
        }
    }
    private void reset(){
        list.forEach(ClassroomSchedule::reset);
    }
    public List<ClassroomSchedule> getClassroomScheduleList() {
        return list;
    }

    public List<String> getFreeClassroomList(BuildingService service, int day, String start, String end) {
        return list.stream()
                .filter(e -> e.isFree(service,day,start, end))
                .map(e -> e.name)
                .sorted((e1,e2)->{
                    if(e1.length()==e2.length())
                        return e1.compareTo(e2);
                    return e1.length()-e2.length();
                })
                .toList();
    }
}
