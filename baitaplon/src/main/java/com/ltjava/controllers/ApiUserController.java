/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.pojo.Major;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Teacher;
import com.ltjava.pojo.User;
import com.ltjava.service.MajorService;
import com.ltjava.service.StudentService;
import com.ltjava.service.TeacherService;
import com.ltjava.service.UserRoleService;
import com.ltjava.service.UserService;
import com.sun.jmx.remote.security.JMXSubjectDomainCombiner;
import java.util.List;
import java.util.Map;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HIEN
 */
@RestController
public class ApiUserController {
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private MajorService majorService;
    
    @PutMapping(path = "/api/user/loadId", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Object[]> loadUserId(@RequestBody Map<String, String> params){
        try{
            String result = this.userService.loadNewUserId(Integer.parseInt(params.get("userRoleId")));
            return new ResponseEntity<>(new Object[]{result}, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path = "/api/user/addStudent", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Object[]> loadStudent(@RequestBody Map<String, String> params){
        try{
            Object[] result = studentService.getStudentAccount(params.get("studentId"));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path = "/api/user/loadStudentAccount", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<List<Student>> loadStudentAccount(){
        try{
            List<Student> kq = this.studentService.getListStudentAccount();
            return new ResponseEntity<>(kq, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path = "/api/user/selectStudent", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Student> selectStudent(@RequestBody Map<String, String> params){
        try{
            Student kq = this.studentService.getStudentById(params.get("studentId"));
            return new ResponseEntity<>(kq, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path = "/api/user/addStudentUser", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<User> addStudentUser(@RequestBody Map<String, String> params){
        try{
            User user = new User(params.get("userId"), 
                    params.get("username"), 
                    params.get("password"), 
                    userRoleService.getUserRole(Integer.parseInt(params.get("userRoleId"))));
            if(userService.addOrUpdate(user)){
                User newUser = userService.getUserById(params.get("userId"));
                return new ResponseEntity<>(newUser, HttpStatus.OK);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path = "/api/user/addTeacherUser", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<User> addTeacherUser(@RequestBody Map<String, String> params){
        try{
            Major majorId = null;
            if(!params.get("majorId").isBlank()&&!params.get("majorId").isEmpty()){
                majorId = majorService.getMajorById(Integer.parseInt(params.get("majorId")));
            }
            Teacher teacher = new Teacher(params.get("userId"), 
                    params.get("firstName"), 
                    params.get("lastName"), 
                    params.get("email"), 
                    params.get("phoneNumber"), 
                    params.get("birthday"), 
                    majorId);
            User user = new User(params.get("userId"), 
                    params.get("username"), 
                    params.get("password"), 
                    userRoleService.getUserRole(Integer.parseInt(params.get("userRoleId"))));
            if(teacherService.addOrUpdateTeacher(teacher)){
                userService.addOrUpdate(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }catch(Exception e){
            
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
