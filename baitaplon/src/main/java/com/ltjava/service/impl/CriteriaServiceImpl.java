/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Criteria;
import com.ltjava.pojo.Major;
import com.ltjava.repository.CriteriaRepository;
import com.ltjava.service.CriteriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class CriteriaServiceImpl implements CriteriaService{
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public List<Criteria> getCriterias(String string) {
        return this.criteriaRepository.getCriterias(string);
    }

    @Override
    public Criteria getCriteriaById(Integer intgr) {
        return this.criteriaRepository.getCriteriaById(intgr);
    }

    @Override
    public boolean addCriteria(Criteria crtr) {
        return this.criteriaRepository.addCriteria(crtr);
    }

    @Override
    public Criteria getCriteriaByName(String name) {
        return this.criteriaRepository.getCriteriaByName(name);
    }
    
}
