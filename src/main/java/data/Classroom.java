package data;

import lombok.Data;

import java.util.UUID;

@Data
public class Classroom {
    UUID uuid;

    public Classroom(String building_name,int floor,int number) {
        init(building_name,floor,number);
    }
    public Classroom(String name) {
        String _building_name = name.substring(0,2);
        int _floor = Integer.parseInt(name.charAt(2)+"");
        int _number = Integer.parseInt(name.substring(3));
        init(_building_name,_floor,_number);
    }
    private void init(String building_name,int floor,int number){
        this.uuid = UUID.randomUUID();
        this.building_name = building_name;
        this.floor = floor;
        this.number = number;
    }
    String building_name;
    int floor;
    int number;

}
