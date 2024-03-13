/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.UserRole;
import com.ltjava.repository.UserRoleRepository;
import com.ltjava.service.UserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Override
    public List<UserRole> getUserRoles() {
        return this.userRoleRepository.getUserRoles();
    }

    @Override
    public UserRole getUserRole(Integer id) {
        return this.userRoleRepository.getUserRole(id);
    }
    
}
