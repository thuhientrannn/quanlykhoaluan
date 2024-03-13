/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.service;

import com.ltjava.pojo.MemberRole;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface MemberRoleService {
    List<MemberRole> getMemberRoles();
    MemberRole getMemberRoleById(Integer id);
    
}
