/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.pojo.Major;
import com.ltjava.pojo.Student;
import com.ltjava.service.MajorService;
import com.ltjava.service.StudentService;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HIEN
 */
@RestController
public class ApiStudentController {
    @Autowired
    private MajorService majorService;
    
    @Autowired
    private StudentService studentService;
    
    @PostMapping(path = "/api/student/class", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Set<com.ltjava.pojo.Class>> listClasses(@RequestBody Map<String,String> params){
        try{
            Major major = majorService.getMajorById(Integer.parseInt(params.get("majorId")));
            Set<com.ltjava.pojo.Class> listClass = major.getClasses();
            return new ResponseEntity<>(listClass, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path = "/api/student/edit", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Student> editStudent(@RequestBody Map<String, String> params){
        try{
            System.out.print(params.get("birthday"));
            this.studentService.updateStudent(params);
            Student student = studentService.getStudentById(params.get("studentId"));
            return new ResponseEntity<>( student,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
