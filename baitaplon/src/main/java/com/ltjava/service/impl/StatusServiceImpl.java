/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Status;
import com.ltjava.repository.StatusRepository;
import com.ltjava.service.StatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class StatusServiceImpl implements StatusService{
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getStatuses() {
        return this.statusRepository.getStatuses();
    }

    @Override
    public Status getStatus(Integer id) {
        return this.statusRepository.getStatus(id);
    }
    
}
