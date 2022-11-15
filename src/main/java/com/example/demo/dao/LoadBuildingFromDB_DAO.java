package com.example.demo.dao;

import com.example.demo.building.BuildingManager;
import com.example.demo.building.classroom.Classroom;
import com.example.demo.dto.ClassroomDatabaseDTO;
import com.example.demo.service.DatabaseInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("DB")
public class LoadBuildingFromDB_DAO implements ILoadBuildingDAO{
    @Autowired
    DatabaseInterfaceService service;
    @Override
    public void loadData(BuildingManager manager) {
        var vo = service.loadClassroomFromCourse();
        for(var data:vo.result()){
            manager.registryRoom((String)data.get("classroom"));
        }
        var vo1 = service.loadClassroomFromRoom();
        for(var data:vo1.result()){
            var dto = new ClassroomDatabaseDTO(manager,data);
            Classroom room = getClassroom(dto);
            dto.getBuilding().addClassroom(room);
        }
        manager.registryRoom();
    }

    public Classroom getClassroom(ClassroomDatabaseDTO dto){
        Classroom room;
        String name = dto.getClassName();
        if(name!=null){
            try {
                var clazz = Class.forName("com.example.demo.building.classroom.custom_room."+name);
                var instance = clazz.getConstructor(ClassroomDatabaseDTO.class).newInstance(dto);
                room = (Classroom) instance;
            } catch (Exception e) {
                room = new Classroom(dto);
            }
        }else{
            room = new Classroom(dto);
        }
        return room;
    }
}
