package com.truefan.taskmgmt.repository;

import com.truefan.taskmgmt.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> findByCreator(Long creator);
}
