package com.task.management.task.management.Controllers;

import com.task.management.task.management.Config.JwtUtil;
import com.task.management.task.management.Config.myCustomUserDetailService;
import com.task.management.task.management.DTO.UserDTO;
import com.task.management.task.management.Entity.UserEntity;
import com.task.management.task.management.Repository.userRepository;
import com.task.management.task.management.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class usersController {

    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private myCustomUserDetailService myCustomUserDetailService;



    @Autowired
    private userRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<UserDTO>> getUser(){
        return ResponseEntity.ok(userService.getUser());
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDTO user){
        return userService.addUser(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO user){
        System.out.println("Login attempt "+user.getUsername());
        System.out.println("Login attempt "+user.getPassword());
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user, HttpServletResponse response){
        System.out.println("Login attempt "+user.getUsername());
        System.out.println("Login attempt "+user.getPassword());
        return userService.login(user.getUsername(), user.getPassword(),response);

    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO user){
        return userService.deleteUser(user);
    }
}
