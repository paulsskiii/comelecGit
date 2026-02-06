package com.taskmaster.api.taskmaster.controller;

import com.taskmaster.api.taskmaster.model.Task;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus; // Import this
// Import this
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
// @Tag groups all endpoints in this class under "Task Management" in Swagger UI
@Tag(name = "Task Management", description = "Endpoints for creating, retrieving, and updating tasks")
public class TaskController {

    private List<Task> taskList = new ArrayList<>();

    public TaskController() {
        // Seeding some initial data
        taskList.add(new Task(1L, "Learn Swagger", "Implement SpringDoc", false));
    }

    @Operation(summary = "Get all tasks", description = "Retrieves a list of all current tasks in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskList;
    }

    @Operation(summary = "Create a new task", description = "Adds a new task to the list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Task created successfully"), // You promised 201 here
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // This line forces the 201 status code
    public Task createTask(@RequestBody Task task) {
        taskList.add(task);
        return task;
    }



}
