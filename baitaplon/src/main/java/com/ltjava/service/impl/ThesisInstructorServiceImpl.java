/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisInstructor;
import com.ltjava.pojo.User;
import com.ltjava.repository.ThesisInstructorRepository;
import com.ltjava.service.ThesisInstructorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class ThesisInstructorServiceImpl implements ThesisInstructorService{
    @Autowired
    private ThesisInstructorRepository thesisInstructorRepository;
    
    @Override
    public List<ThesisInstructor> getThesisInstructors(Thesis thesis, User user) {
        return this.thesisInstructorRepository.getThesisInstructors(thesis, user);
    }

    @Override
    public boolean addOrUpdateThesisInstructor(ThesisInstructor ts) {
        return this.thesisInstructorRepository.addOrUpdateThesisInstructor(ts);
    }

    @Override
    public boolean removeThesisInstructors(Thesis thesis) {
        return this.thesisInstructorRepository.removeThesisInstructors(thesis);
    }
    
}
