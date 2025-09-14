package com.task.management.task.management.Entity;

import jakarta.persistence.*;

@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taskName;
    private String taskDescription;
    private String taskStatus;

    @ManyToOne
    @JoinColumn(name="project_id")
    private projectEntity project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public projectEntity getProject() {
        return project;
    }

    public void setProject(projectEntity project) {
        this.project = project;
    }
}
