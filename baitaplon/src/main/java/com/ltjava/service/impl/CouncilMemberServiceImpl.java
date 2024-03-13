/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Council;
import com.ltjava.pojo.CouncilMember;
import com.ltjava.pojo.User;
import com.ltjava.repository.CouncilMemberRepository;
import com.ltjava.service.CouncilMemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class CouncilMemberServiceImpl implements CouncilMemberService{
    @Autowired
    private CouncilMemberRepository councilMemberRepository;
    
    @Override
    public List<CouncilMember> getMemberByCouncil(Council c) {
        return this.councilMemberRepository.getMemberByCouncil(c);
    }

    @Override
    public List<CouncilMember> getCouncilByMember(User u) {
        return this.councilMemberRepository.getCouncilByMember(u);
    }

    @Override
    public List<Object[]> getListCouncilMember(List<Council> list) {
        return this.councilMemberRepository.getListCouncilMember(list);
    }

    @Override
    public boolean addCouncilMember(CouncilMember cm) {
        return this.councilMemberRepository.addCouncilMember(cm);
    }
    
}
