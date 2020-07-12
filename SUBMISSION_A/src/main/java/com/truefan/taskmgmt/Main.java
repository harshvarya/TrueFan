package com.truefan.taskmgmt;

import com.truefan.taskmgmt.service.UserTaskService;
import com.truefan.taskmgmt.service.UserTasksServiceFacade;
import com.truefan.taskmgmt.util.LoadDataUtil;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        LoadDataUtil.loadUsers(args[0] + "/users.txt");
        LoadDataUtil.loadTasks(args[0] + "/tasks.txt");
        LoadDataUtil.loadUserTasks(args[0] + "/users-tasks.txt");

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        UserTaskService userTasksService = UserTasksServiceFacade.getService();
        Set<Integer> registeredUsers = userTasksService.getRegisteredUsers();
        registeredUsers.forEach(id -> System.out.println("Tasks created by User[Id = " + id + "] : " + userTasksService.getCreatedTasks(id)));

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        userTasksService.assignTask(1, 3);
        userTasksService.assignTask(1, 4);
        userTasksService.assignTask(1, 5);
        userTasksService.assignTask(2, 1);
        userTasksService.assignTask(2, 2);

        registeredUsers.forEach(id -> System.out.println("Tasks assigned to User[Id = " + id + "] : " + userTasksService.getAssignedTasks(id)));
    }

}
