/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name="major")
public class Major implements Serializable{

    /**
     * @return the majorYears
     */
    public Set<MajorYear> getMajorYears() {
        return majorYears;
    }

    /**
     * @param majorYears the majorYears to set
     */
    public void setMajorYears(Set<MajorYear> majorYears) {
        this.majorYears = majorYears;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String word;
    @JsonIgnore
    @OneToMany(mappedBy = "majorId", fetch = FetchType.EAGER )
    private Set<Class> classes; 
    @JsonIgnore
    @OneToMany(mappedBy = "majorId", fetch = FetchType.EAGER)
    private Set<Teacher> teachers;
    @JsonIgnore
    @OneToMany(mappedBy = "majorId", fetch = FetchType.EAGER)
    private Set<MajorYear> majorYears;
    
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
     * @return the classes
     */
    public Set<Class> getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the teachers
     */
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * @param teachers the teachers to set
     */
    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
    
}
