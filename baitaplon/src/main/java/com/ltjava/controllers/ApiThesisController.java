/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.pojo.Major;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Teacher;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisInstructor;
import com.ltjava.pojo.User;
import com.ltjava.service.StudentService;
import com.ltjava.service.TeacherService;
import com.ltjava.service.ThesisInstructorService;
import com.ltjava.service.ThesisService;
import com.ltjava.service.UserService;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HIEN
 */
@RestController
public class ApiThesisController {
    @Autowired
    UserService userService;
    
    @Autowired
    StudentService studentService;
    
    @Autowired
    ThesisService thesisService;
    
    @Autowired
    ThesisInstructorService thesisInstructorService;
    
    @Autowired
    TeacherService teacherService;
    
    @PutMapping(path = "/api/thesis/user", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Teacher> getUser(@RequestBody Map<String,String> params){
        try{
            Teacher teacher = teacherService.getTeacherById(params.get("userId"));
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping(path = "/api/thesis/student", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Student> getStudent(@RequestBody Map<String,String> params){
        try{
            Student student = studentService.getStudentById(params.get("studentId"));
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path = "/api/thesis/edit", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Thesis> editThesis(@RequestBody Map<String,String> params){
        try{
            Thesis thesis = this.thesisService.getThesisById(Integer.parseInt(params.get("thesisId")));
            User reviewer = this.userService.getUserById(params.get("reviewerId"));
            Student s1 = this.studentService.getStudentById(params.get("student1"));
            Student s2 = this.studentService.getStudentById(params.get("student2"));
            User ins1 = this.userService.getUserById(params.get("instructor1"));
            User ins2 = this.userService.getUserById(params.get("instructor2"));
            this.thesisInstructorService.removeThesisInstructors(thesis);
            for(Student student:thesis.getStudents()){
                student.setThesisId(null);
                this.studentService.addOrUpdateStudent(student);
            }
            if(s1!=null){
                s1.setThesisId(thesis);
                this.studentService.addOrUpdateStudent(s1);
            }
            if(s2!=null){
                s2.setThesisId(thesis);
                this.studentService.addOrUpdateStudent(s2);
            }
            if(ins1!=null){
                this.thesisInstructorService.addOrUpdateThesisInstructor(new ThesisInstructor(thesis, ins1));
            }
            if(ins2!=null){
                this.thesisInstructorService.addOrUpdateThesisInstructor(new ThesisInstructor(thesis, ins2));
            }
            thesis.setTopic(params.get("topic"));
            thesis.setDescription(params.get("description"));
            thesis.setReviewerId(reviewer);
            if(this.thesisService.addOrUpdateThesis(thesis)){
                return new ResponseEntity<>(thesis, HttpStatus.OK);
            }
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
