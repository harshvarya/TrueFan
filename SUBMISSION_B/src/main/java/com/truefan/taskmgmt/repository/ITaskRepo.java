package com.truefan.taskmgmt.repository;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;

import java.util.List;

public interface ITaskRepo {
    Task create(String activityType, Integer creator, String details) throws TaskNotCreatedException;

    Task getTask(Integer taskId) throws TaskNotCreatedException;

    List<Task> getCreatedTasks(Integer userId);
}
