package com.example.demo;

import com.example.demo.building.BuildingManager;
import com.example.demo.building.BuildingRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DemoApplication  {
    public static final String SEMESTER = "1111";
    public static final BuildingManager defaultBuildingManager = new BuildingManager();
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}