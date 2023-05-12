package com.ukrposhta.service;

import com.ukrposhta.model.Task;
import java.util.List;

public interface TaskService {
    Task save(Task task);

    List<Task> findAll();

    Task findById(Long id);

    List<Task> findAllByIdIn(List<Long> taskIds);

    void deleteById(Long id);
}
