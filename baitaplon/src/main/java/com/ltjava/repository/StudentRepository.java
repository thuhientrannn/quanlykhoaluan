/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.repository;

import com.ltjava.pojo.Major;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HIEN
 */
public interface StudentRepository {
    List<Student> getStudents(Map<String,String> params);
    Student getStudentById(String id);
    boolean addOrUpdateStudent(Student s);
    boolean addThesis(Student s, Thesis t);
    Object[] getStudentAccount(String id);
    List<Student> getListStudentAccount();
    boolean updateStudent(Map<String, String> params);
    String loadNewStudentId();
}
