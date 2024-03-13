/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "council_member")
public class CouncilMember implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "council_id", referencedColumnName = "id")
    private Council councilId;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @ManyToOne
    @JoinColumn(name= "member_role", referencedColumnName = "id")
    private MemberRole memberRole;
    
    public CouncilMember(){
        
    }
    
    public CouncilMember(Council c, User u, MemberRole mr){
        this.councilId = c;
        this.userId = u;
        this.memberRole = mr;
    }

    /**
     * @return the councilId
     */
    public Council getCouncilId() {
        return councilId;
    }

    /**
     * @param councilId the councilId to set
     */
    public void setCouncilId(Council councilId) {
        this.councilId = councilId;
    }

    /**
     * @return the userId
     */
    public User getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(User userId) {
        this.userId = userId;
    }

    /**
     * @return the memberRole
     */
    public MemberRole getMemberRole() {
        return memberRole;
    }

    /**
     * @param memberRole the memberRole to set
     */
    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }
    
}
