package com.example.TaskMaster;

import io.swagger.v3.oas.annotations.media.Schema;

public class Task {
    @Schema(description = "The unique ID of the task", example = "101")
    private Long id;

    @Schema(description = "Title of the task", example = "Buy Groceries", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Detailed description", example = "Milk, Eggs, Bread")
    private String description;

    @Schema(description = "Completion status", example = "false")
    private boolean completed;

    // 1. Add No-Arg Constructor (Required for JSON Deserialization)
    public Task() {
    }

    // Constructor
    public Task(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
