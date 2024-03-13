/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jdk.jfr.Timestamp;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "council")
public class Council implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean active;
    private String name;
    @Column(name = "max_member_quantity")
    private Integer maxMemberQuantity;
    @JsonIgnore
    @Column(name = "created_date")
    private Date createdDate;
    @JsonIgnore
    @OneToMany(mappedBy = "councilId", fetch = FetchType.EAGER)
    private Set<CouncilMember> members;
    @JsonIgnore
    @OneToMany(mappedBy = "councilId", fetch = FetchType.EAGER)
    private Set<Thesis> thesises;
    
    
    public Council(){
        
    }
    
    public Council(String name){
        this.name = name;
    }

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
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    
    /**
     * @return the thesises
     */
    public Set<Thesis> getThesises() {
        return thesises;
    }

    /**
     * @param thesises the thesises to set
     */
    public void setThesises(Set<Thesis> thesises) {
        this.thesises = thesises;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the maxMemberQuantity
     */
    public Integer getMaxMemberQuantity() {
        return maxMemberQuantity;
    }

    /**
     * @param maxMemberQuantity the maxMemberQuantity to set
     */
    public void setMaxMemberQuantity(Integer maxMemberQuantity) {
        this.maxMemberQuantity = maxMemberQuantity;
    }

    

    
}
