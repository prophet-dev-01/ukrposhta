package com.ukrposhta.service;

import com.ukrposhta.model.Task;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface TaskService {
    Task save(Task task);

    List<Task> findAll();

    List<Task> findAll(PageRequest pageRequest);

    Task findById(Long id);

    List<Task> findAllByIdIn(List<Long> taskIds);

    void deleteById(Long id);
}
