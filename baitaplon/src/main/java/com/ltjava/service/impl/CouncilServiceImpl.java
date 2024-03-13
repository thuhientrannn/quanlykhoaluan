/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Council;
import com.ltjava.repository.CouncilRepository;
import com.ltjava.service.CouncilService;
import com.ltjava.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class CouncilServiceImpl implements CouncilService{
    @Autowired
    private CouncilRepository councilRepository;
    
    @Override
    public List<Council> getCouncils(String kw) {
        return this.councilRepository.getCouncils(kw);    }

    @Override
    public boolean addCouncil(Council c) {
        c.setActive(Boolean.TRUE);
        return this.councilRepository.addCouncil(c);
    }

    @Override
    public Council getCouncilById(Integer intgr) {
        return this.councilRepository.getCouncilById(intgr);
    }

    @Override
    public boolean lockCouncil(Council cncl) {
        return this.councilRepository.lockCouncil(cncl);
    }

    @Override
    public boolean removeCouncil(Council cncl) {
        this.councilRepository.removeThesises(cncl);
        this.councilRepository.removeCouncilMembers(cncl);
        return this.councilRepository.removeCouncil(cncl);
    }

    @Override
    public boolean removeCouncilMembers(Council cncl) {
        return this.councilRepository.removeCouncilMembers(cncl);
    }


    @Override
    public boolean removeThesises(Council cncl) {
        return this.councilRepository.removeThesises(cncl);
    }

    @Override
    public boolean unlockCouncil(Council cncl) {
        return this.councilRepository.unlockCouncil(cncl);
    }
    
}
