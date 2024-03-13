/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "member_role")
public class MemberRole implements Serializable{
    @Id
    private Integer id;
    private String role;
    private String rolev;
    @OneToMany(mappedBy = "memberRole")
    private Set<CouncilMember> members;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the members
     */
    public Set<CouncilMember> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(Set<CouncilMember> members) {
        this.members = members;
    }

    /**
     * @return the rolev
     */
    public String getRolev() {
        return rolev;
    }

    /**
     * @param rolev the rolev to set
     */
    public void setRolev(String rolev) {
        this.rolev = rolev;
    }
    
}
