package com.example.demo.building.classroom;

import java.util.HashSet;
import java.util.Set;

public class ClassroomProperties {
    public static final String Computer = "Computer";
    public static final String SelfLearn = "SelfLearn";
    public static final String Office = "Office";
    public static final String Repair = "Repair";
    public static final String Playground = "Playground";
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
        return hasProperty(Computer);
    }

    public boolean isSelfLearnRoom() {
        return hasProperty(SelfLearn);
    }

    public boolean isValid() {
        return !(hasProperty(Office) || hasProperty(Repair));
    }

    public boolean isValidForFreeUse() {
        return isValid() && !(isPlayground() || isAutoLock());
    }

    public boolean canSpeak() {
        return !hasProperty(SelfLearn);
    }

    public boolean isPlayground() {
        return hasProperty(Playground);
    }

}
