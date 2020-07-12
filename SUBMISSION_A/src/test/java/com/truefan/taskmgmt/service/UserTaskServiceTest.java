package com.truefan.taskmgmt.service;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.domain.TaskType;
import com.truefan.taskmgmt.domain.User;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;
import com.truefan.taskmgmt.exception.UserAlreadyCreatedException;
import com.truefan.taskmgmt.exception.UserNotCreatedException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserTaskServiceTest {

    static UserTaskService userTaskService;

    @BeforeClass
    public static void init() throws Exception {
        userTaskService = UserTasksServiceFacade.getService();
        userTaskService.registerUser("n1", "e1");
        userTaskService.registerUser("n2", "e2");
        userTaskService.registerUser("n3", "e3");
        userTaskService.createTask(1, "sing", "khandala");
        userTaskService.createTask(2, "dance", "party");
        userTaskService.createTask(3, "cook", "nonveg");
        userTaskService.assignTask(1, 3);
        userTaskService.assignTask(1, 2);
        userTaskService.assignTask(2, 1);
        userTaskService.assignTask(3, 2);
    }

    @Test
    public void testUserCreation1() throws Exception {
        User user = userTaskService.registerUser("n4", "e4");
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getName().equalsIgnoreCase("n4"));
        Assert.assertTrue(user.getEmail().equalsIgnoreCase("e4"));
    }

    @Test(expected = UserAlreadyCreatedException.class)
    public void testUserCreation2() throws Exception {
        userTaskService.registerUser("n1", "e1");
    }

    @Test(expected = UserNotCreatedException.class)
    public void testTaskCreation1() throws Exception {
        userTaskService.createTask(40, "sing", "khandala");
    }

    @Test(expected = TaskNotCreatedException.class)
    public void testTaskCreation3() throws Exception {
        userTaskService.createTask(3, "random", "random wrong task");
    }

    @Test
    public void testTaskCreation2() throws Exception {
        Task task = userTaskService.createTask(2, "paint", "image");
        Assert.assertNotNull(task);
        Assert.assertTrue(task.getCreator() == 2);
        Assert.assertTrue(task.getDetails().equalsIgnoreCase("image"));
        Assert.assertTrue(task.getTaskType() == TaskType.PAINT);
    }

    @Test
    public void testAssignedTask() {
        List<Task> tasks = userTaskService.getAssignedTasks(1);
        Assert.assertTrue(tasks.size() == 2);
        Assert.assertTrue(tasks.get(0).getId() == 3);
        Assert.assertTrue(tasks.get(1).getId() == 2);
    }

    @Test
    public void testCreatedTasks() {
        List<Task> tasks = userTaskService.getCreatedTasks(1);
        Assert.assertTrue(tasks.size() == 1);
        Assert.assertTrue(tasks.get(0).getId() == 1);
        Assert.assertTrue(tasks.get(0).getDetails() == "khandala");
    }
}
