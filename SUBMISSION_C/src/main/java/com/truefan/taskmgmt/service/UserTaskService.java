package com.truefan.taskmgmt.service;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.domain.User;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;
import com.truefan.taskmgmt.exception.UserAlreadyCreatedException;
import com.truefan.taskmgmt.exception.UserNotCreatedException;
import com.truefan.taskmgmt.repository.TaskRepo;
import com.truefan.taskmgmt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserTaskService {

    private static final List<String> TASK_TYPES = Arrays.asList("play", "sing", "paint", "cook", "dance");

    private UserRepo userRepo;
    private TaskRepo taskRepo;

    @Autowired
    UserTaskService(TaskRepo taskRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    public User registerUser(String name, String email) throws UserAlreadyCreatedException {
        Optional<User> opUser = userRepo.findByEmail(email);
        if (opUser.isPresent()) {
            throw new UserAlreadyCreatedException("User already exists for email " + email);
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepo.save(user);
        return user;
    }

    public Task createTask(Long userId, String activityType, String details) throws UserNotCreatedException, TaskNotCreatedException {
        if (!userRepo.findById(userId).isPresent()) {
            throw new UserNotCreatedException("No user exists for id " + userId);
        }
        if (!TASK_TYPES.contains(activityType)) {
            throw new TaskNotCreatedException("Use suitable Activity Type among : " + TASK_TYPES);
        }
        Task task = new Task();
        task.setCreator(userId);
        task.setTaskType(activityType);
        task.setDetails(details);
        taskRepo.save(task);
        return task;
    }

    public List<Task> getCreatedTasks(Long userId) {
        return taskRepo.findByCreator(userId);
    }

    public Task assignTask(Long userId, Long taskId) throws UserNotCreatedException, TaskNotCreatedException {
        Optional<User> opUser = userRepo.findById(userId);
        if (opUser.isPresent()) {
            Optional<Task> opTask = taskRepo.findById(taskId);
            if (opTask.isPresent()) {
                User user = opUser.get();
                user.getTasks().add(taskId);
                userRepo.save(user);
                return opTask.get();
            }
            throw new TaskNotCreatedException("No Task exists for id " + taskId);
        }
        throw new UserNotCreatedException("No user exists for id " + userId);
    }

    public List<Task> getAssignedTasks(Long userId) throws UserNotCreatedException {
        Optional<User> opUser = userRepo.findById(userId);
        if (opUser.isPresent()) {
            List<Task> tasks = new ArrayList<>();

            opUser.get().getTasks().forEach(taskId -> {
                taskRepo.findById(taskId).ifPresent(tasks::add);
            });

            return tasks;
        }
        throw new UserNotCreatedException("No user exists for id " + userId);
    }
}
