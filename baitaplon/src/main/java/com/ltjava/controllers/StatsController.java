/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HIEN
 */
@Controller
public class StatsController {
    @Autowired
    private StatsService statsService;
    
    @RequestMapping("/stats")
    public String stats(Model model){
        model.addAttribute("thesisStats", statsService.thesisStats());
        model.addAttribute("scoreStats", statsService.scoreStats());
        return "stats";
    }
}
