/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.MemberRole;
import com.ltjava.repository.MemberRoleRepository;
import com.ltjava.service.MemberRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class MemberRoleServiceImpl implements MemberRoleService{
    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Override
    public List<MemberRole> getMemberRoles() {
        return this.memberRoleRepository.getMemberRoles();
    }

    @Override
    public MemberRole getMemberRoleById(Integer id) {
        return this.memberRoleRepository.getMemberRoleById(id);
    }
    
}
