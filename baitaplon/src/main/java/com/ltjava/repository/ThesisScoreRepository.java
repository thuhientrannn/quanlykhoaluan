/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.repository;

import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisScore;
import com.ltjava.pojo.User;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface ThesisScoreRepository {
    List<Object[]> getThesisScores(Integer councilId);
    ThesisScore getThesisScoreById(Integer id);
    boolean addOrUpdate(ThesisScore c);
    ThesisScore getThesisScoreByUTC(Integer thesisCriteriaId, String userId);
    List<Object[]> getListAvgScoreOfCriteria(Integer thesisId);
    
}
