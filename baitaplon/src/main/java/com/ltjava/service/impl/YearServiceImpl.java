/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Year;
import com.ltjava.repository.YearRepository;
import com.ltjava.service.YearService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class YearServiceImpl implements YearService{
    @Autowired
    private YearRepository yearRepository;
    
    @Override
    public List<Year> getYears(String kw) {
        return this.yearRepository.getYears(kw);
    }
    
}
