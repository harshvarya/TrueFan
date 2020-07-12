package com.truefan.taskmgmt.repository;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.domain.TaskType;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserTaskRepo implements  IUserTaskRepo {

    private final Map<Integer, List<Integer>> USER_TASKS = new HashMap<>();

    @Override
    public void assign(Integer userId, Integer taskId) {
        USER_TASKS.putIfAbsent(userId, new ArrayList<>());
        USER_TASKS.get(userId).add(taskId);
    }

    @Override
    public List<Integer> getAssignedTasksId(Integer userId) {
        return USER_TASKS.get(userId);
    }
}
