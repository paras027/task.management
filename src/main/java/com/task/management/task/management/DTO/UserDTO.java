package com.task.management.task.management.DTO;

import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    @Column(unique = true)
    private String username;
    private String password;

    private List<TaskDTO> task = new ArrayList<TaskDTO>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TaskDTO> getTask() {
        return task;
    }

    public void setTask(List<TaskDTO> task) {
        this.task = task;
    }


}
