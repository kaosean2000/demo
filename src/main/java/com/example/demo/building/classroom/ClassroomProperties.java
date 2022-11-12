package com.example.demo.building.classroom;

import java.util.HashSet;
import java.util.Set;

public class ClassroomProperties {
    Set<String> properties = new HashSet<>();
    public void addProperty(String property) {
        properties.add(property);
    }
    public boolean hasProperty(String property) {
        return properties.contains(property);
    }

    public boolean isAutoLock() {
        return !isSelfLearnRoom() && isComputerRoom();
    }

    public boolean isComputerRoom() {
        return hasProperty("ComputerCP");
    }

    public boolean isSelfLearnRoom() {
        return hasProperty("SelfLearnCP");
    }

    public boolean isValid() {
        return !(hasProperty("OfficeCP") || hasProperty("RepairCP"));
    }

    public boolean isValidForFreeUse() {
        return isValid() && !(isPlayground() || isAutoLock());
    }

    public boolean canSpeak() {
        return !hasProperty("SelfLearnCP");
    }

    public boolean isPlayground() {
        return hasProperty("PlaygroundCP");
    }

}
