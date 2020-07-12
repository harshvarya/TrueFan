package com.truefan.taskmgmt.repository;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.domain.TaskType;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskRepo implements  ITaskRepo {

    private final Map<Integer, Task> TASKS = new HashMap<>();

    @Override
    public Task create(String activityType, Integer creator, String details) throws TaskNotCreatedException {
        TaskType tt = TaskType.get(activityType);
        if (tt == TaskType.NO_ACTION) {
            throw new TaskNotCreatedException("Use suitable Activity Type [sing, dance, play, cook, paint]");
        }
        Task task = new Task();
        task.setCreator(creator);
        task.setTaskType(tt);
        task.setDetails(details);

        TASKS.put(task.getId(), task);
        return task;
    }

    @Override
    public Task getTask(Integer taskId) throws TaskNotCreatedException {
        Task task = TASKS.get(taskId);
        if (task == null) {
            throw new TaskNotCreatedException("No task exists for id " + taskId);
        }
        return task;
    }

    @Override
    public List<Task> getCreatedTasks(Integer userId) {
        return TASKS.values().stream().filter(task-> task.getCreator().equals(userId)).collect(Collectors.toList());
    }
}
