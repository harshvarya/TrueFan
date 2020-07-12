package com.truefan.taskmgmt.repository;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;

import java.util.List;

public interface IUserTaskRepo {
    void assign(Integer userId, Integer taskId);

    List<Integer> getAssignedTasksId(Integer userId);
}
