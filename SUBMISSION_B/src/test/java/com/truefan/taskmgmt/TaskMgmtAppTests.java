package com.truefan.taskmgmt;

import com.truefan.taskmgmt.domain.Task;
import com.truefan.taskmgmt.domain.User;
import com.truefan.taskmgmt.dto.ResponseDto;
import com.truefan.taskmgmt.dto.ResponseDto.OperationType;
import com.truefan.taskmgmt.dto.TaskDto;
import com.truefan.taskmgmt.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TaskMgmtAppTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test1CreateUser() throws Exception {

        final String baseUrl = "http://localhost:" + port + "/taskmgmt/register_user";
        URI uri = new URI(baseUrl);

        UserDto user = new UserDto();
        user.setEmail("user1@gmail.com");
        user.setName("user1");

        ResponseEntity<ResponseDto> result = restTemplate.postForEntity(uri, user, ResponseDto.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        ResponseDto responseDto = result.getBody();
        Assertions.assertEquals(OperationType.REGISTER_USER.getType(), responseDto.getOperation());
        Assertions.assertEquals("User user1 is registered with Id 1", responseDto.getOutput());
        LinkedHashMap task1 = (LinkedHashMap) responseDto.getData();
        Assertions.assertEquals(user.getName(), task1.get("name"));
        Assertions.assertEquals(user.getEmail(), task1.get("email"));
    }

    @Test
    public void test2CreateTask() throws Exception {

        final String baseUrl = "http://localhost:" + port + "/taskmgmt/create_task/1";
        URI uri = new URI(baseUrl);

        TaskDto task = new TaskDto();
        task.setActivityType("COOK");
        task.setDetails("veg-biryani");

        ResponseEntity<ResponseDto> result = restTemplate.postForEntity(uri, task, ResponseDto.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        ResponseDto responseDto = result.getBody();
        Assertions.assertEquals(OperationType.CREATE_TASK.getType(), responseDto.getOperation());
        Assertions.assertEquals("User 1 created a new task 1", responseDto.getOutput());
        LinkedHashMap task1 = (LinkedHashMap) responseDto.getData();
        Assertions.assertEquals("COOK", task1.get("taskType"));
        Assertions.assertEquals("veg-biryani", task1.get("details"));
        Assertions.assertEquals(1, task1.get("creator"));
    }

    @Test
    public void test3AssignTask() throws Exception {

        final String baseUrl = "http://localhost:" + port + "/taskmgmt/assign_task/1/1";
        URI uri = new URI(baseUrl);

        ResponseEntity<ResponseDto> result = restTemplate.postForEntity(uri, "", ResponseDto.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        ResponseDto responseDto = result.getBody();
        Assertions.assertEquals(OperationType.ASSIGN_TASK.getType(), responseDto.getOperation());
        Assertions.assertEquals("Task 1 assigned to User 1", responseDto.getOutput());
        LinkedHashMap task1 = (LinkedHashMap) responseDto.getData();
        Assertions.assertEquals("COOK", task1.get("taskType"));
        Assertions.assertEquals("veg-biryani", task1.get("details"));
        Assertions.assertEquals(1, task1.get("creator"));
    }

    @Test
    public void test4CreatedTask() throws Exception {

        final String baseUrl = "http://localhost:" + port + "/taskmgmt/tasks/creator/1";
        URI uri = new URI(baseUrl);

        ResponseEntity<ResponseDto> result = restTemplate.getForEntity(uri, ResponseDto.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        ResponseDto responseDto = result.getBody();
        Assertions.assertEquals(OperationType.CREATE_TASK.getType(), responseDto.getOperation());
        Assertions.assertEquals("Tasks created by User 1", responseDto.getOutput());
        List list = (List) responseDto.getData();
        LinkedHashMap task1 = (LinkedHashMap) list.get(0);
        Assertions.assertEquals("COOK", task1.get("taskType"));
        Assertions.assertEquals("veg-biryani", task1.get("details"));
        Assertions.assertEquals(1, task1.get("creator"));
    }

    @Test
    public void test5AssignedTask() throws Exception {

        final String baseUrl = "http://localhost:" + port + "/taskmgmt/tasks/assignee/1";
        URI uri = new URI(baseUrl);

        ResponseEntity<ResponseDto> result = restTemplate.getForEntity(uri, ResponseDto.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        ResponseDto responseDto = result.getBody();
        Assertions.assertEquals(OperationType.MY_TASKS.getType(), responseDto.getOperation());
        Assertions.assertEquals("Tasks assigned to User 1", responseDto.getOutput());
        List<LinkedHashMap> list = (List<LinkedHashMap>) responseDto.getData();
        Assertions.assertEquals(1, list.size());
        LinkedHashMap task1 = (LinkedHashMap) list.get(0);
        Assertions.assertEquals("COOK", task1.get("taskType"));
        Assertions.assertEquals("veg-biryani", task1.get("details"));
        Assertions.assertEquals(1, task1.get("creator"));
    }
}
