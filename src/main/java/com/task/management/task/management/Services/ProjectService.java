package com.task.management.task.management.Services;

import com.task.management.task.management.DTO.TaskDTO;
import com.task.management.task.management.DTO.projectDTO;
import com.task.management.task.management.Entity.TaskEntity;
import com.task.management.task.management.Entity.UserEntity;
import com.task.management.task.management.Entity.projectEntity;
import com.task.management.task.management.Repository.projectRepository;
import com.task.management.task.management.Repository.userRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private projectRepository projectRepository;
    private userRepository userRepository;

    @Autowired
    public ProjectService(projectRepository projectRepository, userRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;

    }


    public ResponseEntity<String> addProject(projectDTO  projectDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userRepository.findByUsername(username);

        projectEntity project = new projectEntity();
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDescription(projectDTO.getProjectDescription());
        project.setUser(user);
        user.getProject().add(project);
        projectRepository.save(project);
        return ResponseEntity.ok().body("Success");
    }

    public ResponseEntity<String> deleteProject(Long id) {
        projectEntity projectEntity = projectRepository.findById(id).get();
        projectRepository.delete(projectEntity);
        return ResponseEntity.ok().body("Success");
    }

    public ResponseEntity<List<projectDTO>> getProject(){

        List<projectEntity> projectEntity = projectRepository.findAll();
        if(projectEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<projectDTO> project = new ArrayList<>();
        for(projectEntity e:projectEntity){
            projectDTO dto = new projectDTO();
            dto.setProjectName(e.getProjectName());
            dto.setProjectDescription(e.getProjectDescription());
            if(e.getTasks().size()>0){
                List<TaskDTO> tasks = new ArrayList<>();
                for(TaskEntity taskDTO:e.getTasks()){
                    TaskDTO taskDTODTO = new TaskDTO();
                    taskDTODTO.setTaskName(taskDTO.getTaskName());
                    taskDTODTO.setTaskDescription(taskDTO.getTaskDescription());
                    taskDTODTO.setTaskStatus(taskDTO.getTaskStatus());

                    tasks.add(taskDTODTO);
                }
                dto.setTasks(tasks);
            }
            project.add(dto);
        }

        return ResponseEntity.ok(project);
    }


    public ResponseEntity<List<projectDTO>> getProjectByName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userRepository.findByUsername(username);

        List<projectEntity> projectEntity = user.getProject();
        System.out.println(projectEntity.size());
        if(projectEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<projectDTO> project = new ArrayList<>();
        for(projectEntity e:projectEntity){
            projectDTO dto = new projectDTO();
            dto.setId(e.getId());
            dto.setProjectName(e.getProjectName());
            dto.setProjectDescription(e.getProjectDescription());
            project.add(dto);
        }

        return ResponseEntity.ok(project);
    }
}
