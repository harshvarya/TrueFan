package com.truefan.taskmgmt.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private Integer id;
    private Integer creator;
    private String details;
    private TaskType taskType;

    private static final AtomicInteger KEY_GENERATOR = new AtomicInteger(0);

    public Task() {
        id = KEY_GENERATOR.incrementAndGet();
    }

    public Integer getId() {
        return id;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", creator=" + creator +
                ", details='" + details + '\'' +
                ", taskType=" + taskType +
                '}';
    }
}
