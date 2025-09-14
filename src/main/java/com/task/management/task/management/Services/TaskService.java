package com.task.management.task.management.Services;

import com.task.management.task.management.DTO.TaskDTO;
import com.task.management.task.management.DTO.UserDTO;
import com.task.management.task.management.DTO.projectDTO;
import com.task.management.task.management.Entity.TaskEntity;
import com.task.management.task.management.Entity.UserEntity;
import com.task.management.task.management.Entity.projectEntity;
import com.task.management.task.management.Repository.projectRepository;
import com.task.management.task.management.Repository.taskRepository;
import com.task.management.task.management.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private taskRepository taskRepository;
    private userRepository userRepository;
    private projectRepository projectRepository;

    @Autowired
    public TaskService(taskRepository taskRepository,userRepository userRepository, projectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }


    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<TaskEntity> tasks =  taskRepository.findAll();

        if(tasks==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<TaskDTO> taskList = new ArrayList<>();
        for(TaskEntity task:tasks){
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTaskName(task.getTaskName());
            taskDTO.setTaskDescription(task.getTaskDescription());
            taskDTO.setTaskStatus(task.getTaskStatus());
            taskList.add(taskDTO);
        }

        return ResponseEntity.ok().body(taskList);
    }

    public ResponseEntity<List<TaskDTO>> getTasksbySearch(String taskname) {

        List<TaskDTO> list = new ArrayList<>();
        List<TaskEntity> tasks = taskRepository.findByTaskName(taskname);

        if(tasks==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        for(TaskEntity task:tasks){
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTaskDescription(task.getTaskDescription());
            taskDTO.setTaskName(task.getTaskName());
            taskDTO.setTaskStatus(task.getTaskStatus());

            list.add(taskDTO);
        }
        return ResponseEntity.ok().body(list);

    }

    public ResponseEntity<String> addTasks(Long id,TaskDTO task) {

        projectEntity project  = projectRepository.findById(id).get();
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskDescription(task.getTaskDescription());
        taskEntity.setTaskName(task.getTaskName());
        taskEntity.setTaskStatus(task.getTaskStatus());
        taskEntity.setProject(project);
        taskRepository.save(taskEntity);

        return ResponseEntity.ok().body("success");
    }


    public ResponseEntity<String> updateTasks(TaskDTO task) {
        Long taskId = task.getId();
        TaskEntity tasks = taskRepository.findById(taskId).get();

        if(tasks==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        taskRepository.save(tasks);
        return ResponseEntity.ok().body("success");

    }

    public ResponseEntity<String> deleteTasks(Long id) {

        TaskEntity tasks = taskRepository.findById(id).get();
        if(tasks==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.delete(tasks);
        return ResponseEntity.ok().body("success");

    }

    public ResponseEntity<List<TaskDTO>> getTasksbyProject(Long id) {

        projectEntity project  = projectRepository.findById(id).get();

        List<TaskEntity> tasks = project.getTasks();
        System.out.println(tasks.size());
        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TaskDTO> task = new ArrayList<>();
        for(TaskEntity e:tasks){
            TaskDTO dto = new TaskDTO();
            dto.setId(e.getId());
            dto.setTaskName(e.getTaskName());
            dto.setTaskDescription(e.getTaskDescription());
            dto.setTaskStatus(e.getTaskStatus());
            task.add(dto);
        }

        return ResponseEntity.ok(task);
    }
}
