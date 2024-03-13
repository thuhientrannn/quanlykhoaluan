/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.repository.StatsRepository;
import com.ltjava.service.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private StatsRepository statsRepository;
    
    @Override
    public List<Object[]> scoreStats() {
        return this.statsRepository.scoreStats();
    }

    @Override
    public List<Object[]> thesisStats() {
        return this.statsRepository.thesisStats();
    }

    @Override
    public List<Object[]> countUserByUserRole() {
        return this.statsRepository.countUserByUserRole();
    }
    
}
