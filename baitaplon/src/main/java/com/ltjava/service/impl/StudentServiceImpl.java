/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Major;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import com.ltjava.repository.StudentRepository;
import com.ltjava.service.StudentService;
import com.ltjava.service.UserRoleService;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents(Map<String, String> kw) {
        return this.studentRepository.getStudents(kw);
    }

    @Override
    public Student getStudentById(String id) {
        return this.studentRepository.getStudentById(id);
    }

    @Override
    public boolean addOrUpdateStudent(Student s) {
        return this.studentRepository.addOrUpdateStudent(s);
    }

    @Override
    public boolean addThesis(Student stdnt, Thesis thesis) {
        return this.studentRepository.addThesis(stdnt, thesis);
    }

    @Override
    public Object[] getStudentAccount(String string) {
        return this.studentRepository.getStudentAccount(string);
    }

    @Override
    public List<Student> getListStudentAccount() {
        return this.studentRepository.getListStudentAccount();
    }

    @Override
    public boolean updateStudent(Map<String, String> map) {
        return this.studentRepository.updateStudent(map);
    }

    @Override
    public String loadNewStudentId() {
        return this.studentRepository.loadNewStudentId();
    }
}
