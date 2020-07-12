package com.truefan.taskmgmt.domain;

public enum TaskType {
    PLAY("play"),
    SING("sing"),
    DANCE("dance"),
    COOK("cook"),
    PAINT("paint"),
    NO_ACTION("no-action");

    private String activityType;

    private TaskType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityType() {
        return activityType;
    }

    public static TaskType get(String activityType) {
        for (TaskType val : TaskType.values()) {
            if (val.activityType.equalsIgnoreCase(activityType))
                return val;
        }
        return NO_ACTION;
    }

    @Override
    public String toString() {
        return activityType;
    }
}
