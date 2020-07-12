package com.truefan.taskmgmt.controller;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.domain.User;
import com.truefan.taskmgmt.dto.ResponseDto;
import com.truefan.taskmgmt.dto.ResponseDto.OperationType;
import com.truefan.taskmgmt.dto.TaskDto;
import com.truefan.taskmgmt.dto.UserDto;
import com.truefan.taskmgmt.exception.TaskNotCreatedException;
import com.truefan.taskmgmt.exception.UserAlreadyCreatedException;
import com.truefan.taskmgmt.exception.UserNotCreatedException;
import com.truefan.taskmgmt.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class UserTaskController {

    @Autowired
    UserTaskService userTaskService;

    @GetMapping("welcome_message")
    public String welcome() {
        return "welcome to task management";
    }

    @PostMapping("register_user")
    public ResponseDto registerUser(@RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOperation(OperationType.REGISTER_USER.getType());
        try {
            User user = userTaskService.registerUser(userDto.getName(), userDto.getEmail());
            responseDto.setOutput("User " + user.getName() + " is registered with Id " + user.getId());
            responseDto.setData(user);

        } catch (UserAlreadyCreatedException ex) {
            responseDto.setOutput("Error in User registration : " + ex.getMessage());
        }
        return responseDto;
    }

    @PostMapping("create_task/{userId}")
    public ResponseDto createTask(@RequestBody TaskDto taskDto, @PathVariable("userId") Integer userId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOperation(OperationType.CREATE_TASK.getType());
        try {
            Task task = userTaskService.createTask(userId, taskDto.getActivityType(), taskDto.getDetails());
            responseDto.setOutput("User " + userId + " created a new task " + task.getId());
            responseDto.setData(task);

        } catch (UserNotCreatedException | TaskNotCreatedException ex) {
            responseDto.setOutput("Error in Task Creation : " + ex.getMessage());
        }
        return responseDto;
    }

    @GetMapping("tasks/creator/{userId}")
    public ResponseDto getCreatedTasks(@PathVariable("userId") Integer userId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOperation(OperationType.CREATE_TASK.getType());

        List<Task> tasks = userTaskService.getCreatedTasks(userId);
        responseDto.setOutput("Tasks created by User " + userId);
        responseDto.setData(tasks);

        return responseDto;
    }

    @PostMapping("assign_task/{taskId}/{userId}")
    public ResponseDto createTask(@PathVariable("taskId") Integer taskId, @PathVariable("userId") Integer userId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOperation(OperationType.ASSIGN_TASK.getType());
        try {
            Task task = userTaskService.assignTask(userId, taskId);
            responseDto.setOutput( "Task "+taskId + " assigned to User " + userId);
            responseDto.setData(task);

        } catch (UserNotCreatedException | TaskNotCreatedException ex) {
            responseDto.setOutput("Error in Task assigning : " + ex.getMessage());
        }
        return responseDto;
    }

    @GetMapping("tasks/assignee/{userId}")
    public ResponseDto getAssignedTasks(@PathVariable("userId") Integer userId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOperation(OperationType.MY_TASKS.getType());

        List<Task> tasks = userTaskService.getAssignedTasks(userId);
        responseDto.setOutput("Tasks assigned to User " + userId);
        responseDto.setData(tasks);

        return responseDto;
    }

}
