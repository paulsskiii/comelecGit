package com.taskmaster.api2;

//import com.taskmaster.api2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus; // Required for ResponseStatus
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Management", description = "Operations for tasks")
public class TaskController {

    private List<Task> taskList = new ArrayList<>();

    public TaskController() {
        taskList.add(new Task(1L, "Learn Swagger", "Setup SpringDoc", false));
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskList;
    }

    @Operation(summary = "Create task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Forces the 201 status code
    public Task createTask(@RequestBody Task task) {
        taskList.add(task);
        return task;
    }
}
