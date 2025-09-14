package com.task.management.task.management.Services;

import com.task.management.task.management.Config.JwtUtil;
import com.task.management.task.management.Config.myCustomUserDetailService;
import com.task.management.task.management.DTO.TaskDTO;
import com.task.management.task.management.DTO.UserDTO;
import com.task.management.task.management.Entity.TaskEntity;
import com.task.management.task.management.Entity.UserEntity;
import com.task.management.task.management.Repository.userRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private userRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private myCustomUserDetailService myCustomUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public UserService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> addUser(UserDTO user) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        try{
            userRepository.save(userEntity);
            return ResponseEntity.ok().body("Success");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error");
        }
    }


    public ResponseEntity<String> login(String username, String password, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        System.out.println("Authentication Success");
        UserDetails userDetails = myCustomUserDetailService.loadUserByUsername(username);
        System.out.println("UserDetails Success"+userDetails);
        String token = jwtUtil.generateToken(userDetails.getUsername());
        System.out.println("token"+token);

        ResponseCookie cookie = ResponseCookie.from("token",token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(3600)
                .sameSite("Lax")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(token);
    }

    public List<UserDTO> getUser(){
        List<UserEntity> users =  userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for(UserEntity user:users){
            UserDTO userDTO = new UserDTO();
            TaskDTO taskDTO = new TaskDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    public ResponseEntity<String> updateUser(UserDTO user){
        UserEntity presentUser = userRepository.findByUsername(user.getUsername());

        if(presentUser!=null){
            presentUser.setPassword(user.getPassword());
            userRepository.save(presentUser);
            return ResponseEntity.ok("Updated Successfully");
        }


        return ResponseEntity.badRequest().body("Error");
    }

    public ResponseEntity<String> deleteUser(UserDTO user){

        UserEntity presentUser = userRepository.findByUsername(user.getUsername());

        if(presentUser!=null){
            userRepository.delete(presentUser);
            return ResponseEntity.ok("Deleted Successfully");
        }


        return ResponseEntity.badRequest().body("Cant found");
    }

}
