# Getting Started

* this application is using in memory db H2
* come to root directory of this project
* run this command : mvn clean package
* run this command : java -jar target/SUBMISSION_B-0.0.1-SNAPSHOT.jar
* application will be up with Base_Url : http//localhost:8080/taskmgmt/
* hit various APIs mentioned in assignment as shown below:
```
    GET : http://localhost:7070/taskmgmt/welcome_message
    Response : welcome to task management
    --------------------------------------------------------------
    POST : http://localhost:7070/taskmgmt/register_user
    Request Body : 
        {
            "name": "USER_2",
            "email": "user_2@true-fan.com"
        }
    Response : 
        {
            "operation": "REGISTER_USER",
            "output": "User USER_2 is registered with Id 1",
            "data": {
                "id": 1,
                "email": "user_2@true-fan.com",
                "name": "USER_2",
                "tasks": []
            }
        }
    --------------------------------------------------------------
    POST : http://localhost:7070/taskmgmt/create_task/1
    Request Body : 
        {
            "activityType": "cook",
            "details": "daal makhni"
        }
    Response : 
        {
            "operation": "CREATE_TASK",
            "output": "User 1 created a new task 1",
            "data": {
                "id": 1,
                "creator": 1,
                "details": "daal makhni",
                "taskType": "cook"
            }
        }
    --------------------------------------------------------------
    POST : http://localhost:7070/taskmgmt/assign_task/<taskId>/<userId>
            http://localhost:7070/taskmgmt/assign_task/1/1
    Request Body : keep it empty
    Response : 
        {
            "operation": "ASSIGN_TASK",
            "output": "Task 1 assigned to User 1",
            "data": {
                "id": 1,
                "creator": 1,
                "details": "daal makhni",
                "taskType": "cook"
            }
        }
    --------------------------------------------------------------
    GET : http://localhost:7070/taskmgmt/tasks/creator/1
    Response : 
        {
            "operation": "CREATE_TASK",
            "output": "Tasks created by User 1",
            "data": [
                {
                    "id": 1,
                    "creator": 1,
                    "details": "daal makhni",
                    "taskType": "cook"
                }
            ]
        }
    --------------------------------------------------------------
    GET : http://localhost:7070/taskmgmt/tasks/assignee/1
    Response : 
        {
            "operation": "MY_TASKS",
            "output": "Tasks assigned to User 1",
            "data": [
                {
                    "id": 1,
                    "creator": 1,
                    "details": "daal makhni",
                    "taskType": "cook"
                }
            ]
        }
    --------------------------------------------------------------
    POST : http://localhost:7070/taskmgmt/update_task/<taskId>/<oldUserId to whom task was assigned>/<newUserId to whom task has to assign>
            http://localhost:7070/taskmgmt/update_task/2/1/2
    Request Body : keep it empty
    Response : 
        {
            "operation": "ASSIGN_TASK",
            "output": "Task 2 assigned to User 2",
        }
    
    

```
* To see H2 tables
```
	hit url : http://localhost:7070/taskmgmt/h2
	enter following values in login form
	username = sa
	password empty
	jdbc url = jdbc:h2:mem:~/truefandb
	now press Test Connection. If this show 'Connection successful' then press connect
```