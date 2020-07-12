## How to run this project?

* come to home directory of this project i.e. SUBMISSION_A.
* copy the absolute path of data directory in this project. This directory contains the input data.
* trigger this command : mvn clean package
* trigger this command : java -jar target/SUBMISSION_A-1.0-SNAPSHOT.jar {paste abosolute path of data directory}

Please see a demo as shown below.
```
    C:\IdeaProjects\SUBMISSION_A>java -jar target\SUBMISSION_A-1.0-SNAPSHOT.jar c:/IdeaProjects/data
    user registered successful as : User{id=1, email='aman@gmail.com', name='aman'}
    user registered successful as : User{id=2, email='mohan@gmail.com', name='mohan'}
    task is created successfully as : Task{id=1, creator=1, details='tera suroor', taskType=sing}
    task is created successfully as : Task{id=2, creator=1, details='everybody dance on the floor', taskType=dance}
    task is created successfully as : Task{id=3, creator=1, details='football', taskType=play}
    task is created successfully as : Task{id=4, creator=2, details='ver biryani', taskType=cook}
    task is created successfully as : Task{id=5, creator=2, details='picaso', taskType=paint}
    error while creating task [userId : 3, ActivityType : paint, details : picaso] : No user exists for id 3
    task [Id : 3] has assigned to user [Id : 1] successfully
    task [Id : 4] has assigned to user [Id : 1] successfully
    task [Id : 5] has assigned to user [Id : 1] successfully
    task [Id : 1] has assigned to user [Id : 2] successfully
    task [Id : 2] has assigned to user [Id : 2] successfully
    error while assigning task [Id : 2] to user [Id : 3] : No user exists for id 3
    error while assigning task [Id : 6] to user [Id : 2] : No task exists for id 6
    
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Tasks created by User[Id = 1] : [Task{id=1, creator=1, details='tera suroor', taskType=sing}, Task{id=2, creator=1, details='everybody dance on the floor', taskType=dance}, Task{id=3, creator=1, details='football', taskType=play}]
    Tasks created by User[Id = 2] : [Task{id=4, creator=2, details='ver biryani', taskType=cook}, Task{id=5, creator=2, details='picaso', taskType=paint}]
    
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Tasks assigned to User[Id = 1] : [Task{id=3, creator=1, details='football', taskType=play}, Task{id=4, creator=2, details='ver biryani', taskType=cook}, Task{id=5, creator=2, details='picaso', taskType=paint}, Task{id=3, creator=1, details='football', taskType=play}, Task{id=4, creator=2, details='ver biryani', taskType=cook}, Task{id=5, creator=2, details='picaso', taskType=paint}]
    Tasks assigned to User[Id = 2] : [Task{id=1, creator=1, details='tera suroor', taskType=sing}, Task{id=2, creator=1, details='everybody dance on the floor', taskType=dance}, Task{id=1, creator=1, details='tera suroor', taskType=sing}, Task{id=2, creator=1, details='everybody dance on the floor', taskType=dance}]
```