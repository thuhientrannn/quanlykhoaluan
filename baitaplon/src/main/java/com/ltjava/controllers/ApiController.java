/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.pojo.Major;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.User;
import com.ltjava.service.MajorService;
import com.ltjava.service.StudentService;
import com.ltjava.service.ThesisService;
import com.ltjava.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HIEN
 */
@RestController
public class ApiController {
    @Autowired
    ThesisService thesisService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    MajorService majorService;
    
    @Autowired
    StudentService studentService;
    
    @GetMapping("/api/thesises")
    public ResponseEntity<List<Thesis>> listThesises(){
        List<Thesis> thesises = this.thesisService.getThesises("");
        
        return new ResponseEntity<>(thesises, HttpStatus.OK);
    }
    
}
