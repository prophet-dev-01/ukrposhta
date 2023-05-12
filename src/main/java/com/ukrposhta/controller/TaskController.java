package com.ukrposhta.controller;

import com.ukrposhta.mapper.TaskMapper;
import com.ukrposhta.model.Task;
import com.ukrposhta.model.dto.request.TaskRequestDto;
import com.ukrposhta.model.dto.response.TaskResponseDto;
import com.ukrposhta.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping
    public TaskResponseDto create(@RequestBody TaskRequestDto requestDto) {
        return taskMapper.toDto(
                taskService.save(taskMapper.toEntity(requestDto)));
    }

    @GetMapping
    public List<TaskResponseDto> findAll() {
        return taskService.findAll()
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskResponseDto findById(@PathVariable Long id) {
        return taskMapper.toDto(taskService.findById(id));
    }

    @PutMapping("/{id}")
    public TaskResponseDto update(@PathVariable Long id, @RequestBody TaskRequestDto requestDto) {
        Task task = taskMapper.toEntity(requestDto);
        task.setId(id);
        return taskMapper.toDto(taskService.save(task));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteById(id);
    }

}
