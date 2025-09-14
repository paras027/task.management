package com.task.management.task.management.Controllers;

import com.task.management.task.management.DTO.projectDTO;
import com.task.management.task.management.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class projectController {

    private ProjectService projectService;


    @Autowired
    public projectController(ProjectService projectService) {
        this.projectService = projectService;

    }

    @GetMapping("/getProjects")
    public ResponseEntity<List<projectDTO>> getProjects() {
        return projectService.getProject();
    }

    @GetMapping("/getProjectbyName")
    public ResponseEntity<List<projectDTO>> getProjectsByName() {
        return projectService.getProjectByName();
    }

    @PostMapping("/addProjects")
    public ResponseEntity<String> addProjects(@RequestBody projectDTO projectDTO) {
        System.out.println("Kuch data aaya : "+projectDTO.getProjectName());
        return projectService.addProject(projectDTO);
    }

    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }
}
