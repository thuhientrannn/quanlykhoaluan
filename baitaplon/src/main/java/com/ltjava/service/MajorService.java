/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.service;

import com.ltjava.pojo.Major;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface MajorService {
    List<Major> getMajors(String kw);
    Major getMajorById(Integer id);
    boolean addMajor(Major m);
    boolean removeMajor(Major m);
}
