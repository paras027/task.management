package com.task.management.task.management.Controllers;

import com.task.management.task.management.DTO.TaskDTO;
import com.task.management.task.management.DTO.UserDTO;
import com.task.management.task.management.Services.TaskService;
import com.task.management.task.management.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class tasksController {

    private TaskService taskService;


    @Autowired
    public tasksController(TaskService taskService) {
        this.taskService = taskService;

    }

    @GetMapping("/getTasks")
    public ResponseEntity<List<TaskDTO>> getTasks(){
        return taskService.getTasks();
    }

    @GetMapping("/getTasksbyProject/{id}")
    public ResponseEntity<List<TaskDTO>> getTasksbyProject(@PathVariable String id){
        Long idd = Long.parseLong(id);
        return taskService.getTasksbyProject(idd);
    }

    @GetMapping("/getTasksbySearch/{title}")
    public ResponseEntity<List<TaskDTO>> getTasksbySearch(@PathVariable String title){
        return taskService.getTasksbySearch(title);
    }

    @PostMapping("/addTasks/{id}")
    public ResponseEntity<String> addTasks(@RequestBody TaskDTO task,@PathVariable String id){
        Long idd = Long.parseLong(id);
        return taskService.addTasks(idd, task);
    }

    @PutMapping("/updateTasks")
    public ResponseEntity<String> updateTasks(@RequestBody TaskDTO task){
        return taskService.updateTasks(task);
    }

    @DeleteMapping("/deleteTasks/{id}")
    public ResponseEntity<String> deleteTasks(@PathVariable Long id){
        return taskService.deleteTasks(id);
    }
}
