package com.truefan.taskmgmt.util;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.domain.User;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;
import com.truefan.taskmgmt.exception.UserAlreadyCreatedException;
import com.truefan.taskmgmt.exception.UserNotCreatedException;
import com.truefan.taskmgmt.service.UserTaskService;
import com.truefan.taskmgmt.service.UserTasksServiceFacade;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadDataUtil {

    private static UserTaskService userTasksService = UserTasksServiceFacade.getService();

    public static void loadUsers(String dataPath) throws IOException {
        File file = new File(dataPath);
        if (file.exists()) {
            Files.lines(Paths.get(file.getAbsolutePath())).skip(1).forEach(line -> {
                String[] data = line.split(",");
                try {
                    User user = userTasksService.registerUser(data[0], data[1]);
                    System.out.println("user registered successful as : " + user);
                } catch (UserAlreadyCreatedException ex) {
                    System.out.println("error while registering user '" + data[1] + "' : " + ex.getMessage());
                }
            });
        }
    }

    public static void loadTasks(String dataPath) throws IOException {
        File file = new File(dataPath);
        if (file.exists()) {
            Files.lines(Paths.get(file.getAbsolutePath())).skip(1).forEach(line -> {
                String[] data = line.split(",");
                try {
                    Task task = userTasksService.createTask(Integer.parseInt(data[0]), data[1], data[2]);
                    System.out.println("task is created successfully as : " + task);

                } catch (TaskNotCreatedException | UserNotCreatedException ex) {
                    System.out.println("error while creating task [userId : " +
                            data[0] + ", ActivityType : " + data[1] + ", details : " + data[2] + "] : " + ex.getMessage());
                }
            });
        }
    }

    public static void loadUserTasks(String dataPath) throws IOException {
        File file = new File(dataPath);
        if (file.exists()) {
            Files.lines(Paths.get(file.getAbsolutePath())).skip(1).forEach(line -> {
                String[] data = line.split(",");
                try {
                    userTasksService.assignTask(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                    System.out.println("task [Id : " + data[1] + "] has assigned to user [Id : " + data[0] + "] successfully ");

                } catch (TaskNotCreatedException | UserNotCreatedException ex) {
                    System.out.println("error while assigning task [Id : " + data[1] + "] to user [Id : " + data[0] + "] : " + ex.getMessage());
                }
            });
        }
    }
}
