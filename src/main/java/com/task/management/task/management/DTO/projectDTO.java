package com.task.management.task.management.DTO;

import com.task.management.task.management.Entity.TaskEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class projectDTO {

    private Long id;
    private String projectName;
    private String projectDescription;

    private List<TaskDTO> tasks = new ArrayList<TaskDTO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
