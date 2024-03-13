/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.repository;

import com.ltjava.pojo.Council;
import com.ltjava.pojo.CouncilMember;
import com.ltjava.pojo.MemberRole;
import com.ltjava.pojo.User;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface CouncilMemberRepository {
    List<CouncilMember> getMemberByCouncil(Council c);
    List<CouncilMember> getCouncilByMember(User u);
    List<Object[]> getListCouncilMember(List<Council> list);
    boolean addCouncilMember(CouncilMember cm);
}
