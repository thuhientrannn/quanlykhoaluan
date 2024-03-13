/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisScore;
import com.ltjava.repository.ThesisScoreRepository;
import com.ltjava.service.ThesisScoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class ThesisScoreServiceImpl implements ThesisScoreService{
    @Autowired
    private ThesisScoreRepository thesisScoreRepository;
    
    @Override
    public List<Object[]> getThesisScores(Integer councilId) {
        return this.thesisScoreRepository.getThesisScores(councilId);
    }

    @Override
    public ThesisScore getThesisScoreById(Integer id) {
        return this.thesisScoreRepository.getThesisScoreById(id); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addOrUpdate(ThesisScore c) {
        return this.thesisScoreRepository.addOrUpdate(c); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //Get ThesisScore by ThesisCriteria and User
    @Override
    public ThesisScore getThesisScoreByUTC(Integer intgr, String string) {
        return this.thesisScoreRepository.getThesisScoreByUTC(intgr, string);
    }

    @Override
    public List<Object[]> getListAvgScoreOfCriteria(Integer thesisId) {
        return this.thesisScoreRepository.getListAvgScoreOfCriteria(thesisId);
    }
}
