/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Major;
import com.ltjava.repository.MajorRepository;
import com.ltjava.service.MajorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class MajorServiceImpl implements MajorService{
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<Major> getMajors(String kw) {
        return this.majorRepository.getMajors(kw);
    }

    @Override
    public Major getMajorById(Integer id) {
        return this.majorRepository.getMajorById(id);
    }

    @Override
    public boolean addMajor(Major m) {
        return this.majorRepository.addMajor(m);
    }

    @Override
    public boolean removeMajor(Major major) {
        return this.majorRepository.removeMajor(major);
    }
    
}
