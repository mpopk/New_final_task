package com.kodilla.Task_final.controller;


import com.kodilla.Task_final.dto.TaskDto;
import com.kodilla.Task_final.exception.IllegalTaskIdException;
import com.kodilla.Task_final.exception.InvalidTaskException;
import com.kodilla.Task_final.exception.NoSuchTaskException;
import com.kodilla.Task_final.exception.UnconfirmedDeletionException;
import com.kodilla.Task_final.mapper.TaskMapper;
import com.kodilla.Task_final.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kodilla.Task_final.exception.InvalidTaskException.InvalidTaskReason.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1")
public class TaskController {

    private TaskService service;
    private TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("taskId") Long taskId) {
        validateLegalId(taskId);
        return new ResponseEntity<>(
                taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(NoSuchTaskException::new)),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/task/deleteTask/{taskId}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable("taskId") Long taskId) {
        validateExistingId(taskId);
        service.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/task/deleteAllTasks")
    public ResponseEntity<TaskDto> deleteAllTasks(@RequestHeader HttpHeaders headers) {
        checkDelConfirm(headers);
        service.deleteAllTasks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(method = RequestMethod.PUT,  value = "/task/updateTask", consumes = "application/json")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto task) {
        validateExisting(task);
        return new ResponseEntity<>(
                taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(task))),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,  value = "/task/createTask", consumes = "application/json")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task) {
        validateNew(task);
        return new ResponseEntity<>(
                taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(task))),
                HttpStatus.CREATED);
    }



    private void validateLegalId(Long taskId) {
        if(taskId == null || taskId <= 0) {
            throw new IllegalTaskIdException();
        }
    }

    private void validateExistingId(Long taskId) {
        validateLegalId(taskId);
        if(!service.taskExists(taskId)) {
            throw new NoSuchTaskException();
        }
    }

    private void validateNew(TaskDto task) {
        if(task.getId() != null) {
            throw new InvalidTaskException(FORCED_ID);
        }
        String title = task.getTitle();
        if(title == null || title.isEmpty()|| title.length() > 20) {
            throw new InvalidTaskException(ILLEGAL_TITLE);
        }
    }

    private void validateExisting(TaskDto task) {
        Long id = task.getId();
        if(id == null || id <= 0) {
            throw new InvalidTaskException(ILLEGAL_ID);
        }
        if(!service.taskExists(id)) {
            throw new InvalidTaskException(NOT_EXIST);
        }
        String title = task.getTitle();
        if(title == null || title.isEmpty() || title.length() > 20) {
            throw new InvalidTaskException(ILLEGAL_TITLE);
        }
    }

    private void checkDelConfirm(HttpHeaders headers) {
        List<String> header = headers.get("confirm-delete-all");
        if (header == null || header.size() == 0 || !"true".equals(header.get(0))) {
            throw new UnconfirmedDeletionException();
        }
    }
}
