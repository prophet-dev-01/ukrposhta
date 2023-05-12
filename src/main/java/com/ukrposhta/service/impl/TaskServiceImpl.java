package com.ukrposhta.service.impl;

import com.ukrposhta.model.Status;
import com.ukrposhta.model.Task;
import com.ukrposhta.repository.TaskRepository;
import com.ukrposhta.service.TaskService;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        if (task.getStatus() == Status.COMPLETED_SUCCESSFUL
                || task.getStatus() == Status.NOT_COMPLETED_SUCCESSFUL) {
            task.setFinishDate(LocalDate.now());
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAll(PageRequest pageRequest) {
        return taskRepository.findAll(pageRequest).toList();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Couldn't find task by id: " + id));
    }

    @Override
    public List<Task> findAllByIdIn(List<Long> taskIds) {
        return taskRepository.findAllByIdIn(taskIds);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
