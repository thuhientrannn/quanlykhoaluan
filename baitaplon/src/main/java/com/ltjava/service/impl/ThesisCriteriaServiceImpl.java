/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.ThesisCriteria;
import com.ltjava.repository.ThesisCriteriaRepository;
import com.ltjava.service.ThesisCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class ThesisCriteriaServiceImpl implements ThesisCriteriaService{
    @Autowired
    private ThesisCriteriaRepository thesisCriteriaRepository;
    
    @Override
    public ThesisCriteria getThesisCriteriaById(Integer id) {
        return this.thesisCriteriaRepository.getThesisCriteriaById(id);
    }

    @Override
    public boolean addOrUpdate(ThesisCriteria c) {
        return this.thesisCriteriaRepository.addOrUpdate(c);
    }
    
}
