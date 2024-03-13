/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.repository;

import com.ltjava.pojo.Criteria;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface CriteriaRepository {
    List<Criteria> getCriterias(String kw);
    Criteria getCriteriaById(Integer id);
    boolean addCriteria(Criteria criteria);
    Criteria getCriteriaByName(String name);
}
