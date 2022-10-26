package service;

import data.Classroom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("LoadClassroomDataFromFileServiceDAO")
public class LoadClassroomDataFromFileDAO implements LoadClassroomDataDAO {

    List<Classroom> classroomList;
    @Override
    public void loadData() {
        classroomList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String s = i+"";
            if(s.length()==1)
                s = "0"+s;
            classroomList.add(new Classroom("ES3"+s));
        }
    }

    @Override
    public List<Classroom> findByBuilding(String name) {
        return classroomList.stream()
                .filter(
                        e->e.getBuilding_name().equals(name)
                ).toList();
    }
}
