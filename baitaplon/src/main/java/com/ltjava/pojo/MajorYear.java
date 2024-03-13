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
@Table(name = "major_year")
public class MajorYear implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "year_id", referencedColumnName = "id")
    private Year yearId;
    @Id
    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    private Major majorId;

    /**
     * @return the yearId
     */
    public Year getYearId() {
        return yearId;
    }

    /**
     * @param yearId the yearId to set
     */
    public void setYearId(Year yearId) {
        this.yearId = yearId;
    }

    /**
     * @return the majorId
     */
    public Major getMajorId() {
        return majorId;
    }

    /**
     * @param majorId the majorId to set
     */
    public void setMajorId(Major majorId) {
        this.majorId = majorId;
    }
}
