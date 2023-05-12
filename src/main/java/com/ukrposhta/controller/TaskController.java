package com.ukrposhta.controller;

import com.ukrposhta.mapper.TaskMapper;
import com.ukrposhta.model.Task;
import com.ukrposhta.model.dto.request.TaskRequestDto;
import com.ukrposhta.model.dto.response.TaskResponseDto;
import com.ukrposhta.service.TaskService;
import com.ukrposhta.util.PageRequestUtil;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final PageRequestUtil pageRequestUtil;

    @PostMapping
    @ApiOperation(value = "crete a new task")
    public TaskResponseDto create(@RequestBody TaskRequestDto requestDto) {
        return taskMapper.toDto(
                taskService.save(taskMapper.toEntity(requestDto)));
    }

    @GetMapping
    @ApiOperation(value = "get task list")
    public List<TaskResponseDto> findAll(@RequestParam(defaultValue = "20") Integer count,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageRequest = pageRequestUtil
                .getPageRequest(count, page, sortBy, "name", "id", "status");
        return taskService.findAll(pageRequest)
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get task by id")
    public TaskResponseDto findById(@PathVariable Long id) {
        return taskMapper.toDto(taskService.findById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update task")
    public TaskResponseDto update(@PathVariable Long id, @RequestBody TaskRequestDto requestDto) {
        Task task = taskMapper.toEntity(requestDto);
        task.setId(id);
        return taskMapper.toDto(taskService.save(task));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete task")
    public void delete(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
