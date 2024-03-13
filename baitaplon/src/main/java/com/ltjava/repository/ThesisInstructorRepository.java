/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.repository;

import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisInstructor;
import com.ltjava.pojo.User;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface ThesisInstructorRepository {
    List<ThesisInstructor> getThesisInstructors(Thesis thesis, User user);
    boolean addOrUpdateThesisInstructor(ThesisInstructor ts);
    boolean removeThesisInstructors(Thesis thesis);
}
