package com.truefan.taskmgmt.service;

import com.truefan.taskmgmt.repository.TaskRepo;
import com.truefan.taskmgmt.repository.UserRepo;
import com.truefan.taskmgmt.repository.UserTaskRepo;

public class UserTasksServiceFacade {

    private static UserTaskService service = new UserTaskService(new TaskRepo(), new UserRepo(), new UserTaskRepo());

    public static UserTaskService getService() {
        return service;
    }
}
