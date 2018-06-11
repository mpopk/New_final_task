package com.kodilla.Task_final.mapper;


import com.kodilla.Task_final.domain.Task;
import com.kodilla.Task_final.dto.TaskDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TaskMapper {
    public Task mapToTask(final TaskDto taskDto){
        return new Task(taskDto.getId(), taskDto.getTitle(), taskDto.getContent());
    }

    public TaskDto mapToTaskDto(final Task task){
        return new TaskDto(task.getId(), task.getTitle(), task.getContent());
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(this::mapToTaskDto)
                .collect(toList());
    }
}
