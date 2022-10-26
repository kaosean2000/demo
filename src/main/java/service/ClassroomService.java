package service;

import data.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    LoadClassroomDataDAO loadDataDAO;
    public ClassroomService(@Qualifier("LoadClassroomDataFromFileServiceDAO") LoadClassroomDataDAO loadDataDAO) {
        this.loadDataDAO = loadDataDAO;

    }
    public void init(){
        loadDataDAO.loadData();
    }
    public List<Classroom> findByBuilding(String name){
         return loadDataDAO.findByBuilding(name);
    }

}
