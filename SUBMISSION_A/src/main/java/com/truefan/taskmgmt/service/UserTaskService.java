package com.truefan.taskmgmt.service;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.domain.User;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;
import com.truefan.taskmgmt.exception.UserAlreadyCreatedException;
import com.truefan.taskmgmt.exception.UserNotCreatedException;
import com.truefan.taskmgmt.repository.ITaskRepo;
import com.truefan.taskmgmt.repository.IUserRepo;
import com.truefan.taskmgmt.repository.IUserTaskRepo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserTaskService {
    private IUserRepo userRepo;
    private ITaskRepo taskRepo;
    private IUserTaskRepo userTaskRepo;

    UserTaskService(ITaskRepo taskRepo, IUserRepo userRepo, IUserTaskRepo userTaskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.userTaskRepo = userTaskRepo;
    }

    public User registerUser(String name, String email) throws UserAlreadyCreatedException {
        return userRepo.register(name, email);
    }

    public Task createTask(Integer userId, String activityType, String details) throws UserNotCreatedException, TaskNotCreatedException {
        userRepo.getUserById(userId);
        Task task = taskRepo.create(activityType, userId, details);
        return task;
    }

    public Task assignTask(Integer userId, Integer taskId) throws UserNotCreatedException, TaskNotCreatedException {
        userRepo.getUserById(userId);
        Task task = taskRepo.getTask(taskId);
        userTaskRepo.assign(userId, task.getId());
        return task;
    }

    public List<Task> getCreatedTasks(Integer userId) {
        return taskRepo.getCreatedTasks(userId);
    }

    public Set<Integer> getRegisteredUsers() {
        return userRepo.getRegisteredUsersId();
    }

    public List<Task> getAssignedTasks(Integer userId) {
        return userTaskRepo.getAssignedTasksId(userId).stream().map(taskId -> {
            Task task = null;
            try {
                task = taskRepo.getTask(taskId);
            } catch (TaskNotCreatedException ex) {
                task = null;
            }
            return task;
        }).collect(Collectors.toList());
    }
}
