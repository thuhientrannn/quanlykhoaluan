/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Council;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisInstructor;
import com.ltjava.pojo.User;
import com.ltjava.repository.ThesisRepository;
import com.ltjava.service.ThesisService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class ThesisServiceImpl implements ThesisService{
    @Autowired
    private ThesisRepository thesisRepository;
    
    @Override
    public List<Thesis> getThesises(String kw) {
        return this.thesisRepository.getThesises(kw);
    }

    @Override
    public boolean addOrUpdateThesis(Thesis thesis) {
        return this.thesisRepository.addOrUpdateThesis(thesis);
    }

    @Override
    public Thesis getThesisById(Integer intgr) {
        return this.thesisRepository.getThesisById(intgr);
    }

    @Override
    public List<Thesis> getThesisesByCouncil(Integer councilId) {
        return this.thesisRepository.getThesisesByCouncil(councilId);
    }

    @Override
    public boolean removeThesis(Thesis thesis) {
        try{
            this.thesisRepository.removeStudents(thesis);
            this.thesisRepository.removeThesisScores(thesis);
            this.thesisRepository.removeThesisInstructors(thesis);
            
            return this.thesisRepository.removeThesis(thesis);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return false;
    }

//    @Override
//    public boolean updateThesis(Integer intgr, String string, String string1, User user, Set<Student> set) {
//        Thesis thesis = this.thesisRepository.getThesisById(intgr);
//        this.thesisRepository.removeStudents(thesis);
//        return this.thesisRepository.updateThesis(intgr, string, string1, user, set);
//    }

    @Override
    public boolean removeStudents(Thesis thesis) {
        return this.thesisRepository.removeStudents(thesis);
    }

    @Override
    public boolean removeThesisScores(Thesis thesis) {
        return this.thesisRepository.removeThesisScores(thesis);
    }

    @Override
    public boolean removeThesisInstructors(Thesis thesis) {
        return this.thesisRepository.removeThesisScores(thesis);
    }
    
}
