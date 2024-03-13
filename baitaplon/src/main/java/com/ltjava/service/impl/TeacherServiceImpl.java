/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Teacher;
import com.ltjava.repository.TeacherRepository;
import com.ltjava.service.TeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Override
    public List<Teacher> getTeachers(String kw) {
        return this.teacherRepository.getTeachers(kw);
    }

    @Override
    public Teacher getTeacherById(String id) {
        return this.teacherRepository.getTeacherById(id);
    }

    @Override
    public boolean addOrUpdateTeacher(Teacher t) {
        return this.teacherRepository.addOrUpdateTeacher(t);
    }
    
}
