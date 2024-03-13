/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.service;

import com.ltjava.pojo.Teacher;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface TeacherService {
    List<Teacher> getTeachers(String kw);
    Teacher getTeacherById(String id);
    boolean addOrUpdateTeacher(Teacher t);
    
}
