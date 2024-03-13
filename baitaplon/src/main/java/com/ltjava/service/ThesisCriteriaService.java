/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.service;

import com.ltjava.pojo.ThesisCriteria;

/**
 *
 * @author HIEN
 */
public interface ThesisCriteriaService {
    ThesisCriteria getThesisCriteriaById(Integer id);
    boolean addOrUpdate(ThesisCriteria c);
    
}
