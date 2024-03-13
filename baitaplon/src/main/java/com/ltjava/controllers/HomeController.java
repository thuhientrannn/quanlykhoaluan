/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.lowagie.text.DocumentException;
import com.ltjava.baitaplon.PDFExporter;
import com.ltjava.pojo.Council;
import com.ltjava.pojo.CouncilMember;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.User;
import com.ltjava.pojo.UserNotification;
import com.ltjava.service.CouncilMemberService;
import com.ltjava.service.CouncilService;
import com.ltjava.service.CriteriaService;
import com.ltjava.service.StatsService;
import com.ltjava.service.StudentService;
import com.ltjava.service.ThesisScoreService;
import com.ltjava.service.ThesisService;
import com.ltjava.service.UserNotificationService;
import com.ltjava.service.UserService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HIEN
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private StatsService statsService;
    
    @Autowired
    private ThesisScoreService thesisScoreService;
    
    @Autowired
    private CriteriaService criteriaService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private ThesisService thesisService;
    
    @Autowired
    private UserNotificationService userNotificationService;
    
    public User user;
    
    @ModelAttribute
    public void commonAttr(Model model, HttpSession Session){
        if(Session.getAttribute("currentUser")!=null){
            user = (User) Session.getAttribute("currentUser");
            model.addAttribute("currentUser", this.userService.getUserById(user.getId()));
            model.addAttribute("listNoti", this.userNotificationService.getUserNotifications(user));
        }
    }
    
    @RequestMapping("/")
    public String index(HttpSession Session){
        if (user!=null && user.getUserRole().getId()==4){
            return "redirect:/student/thesisInfo/"+user.getStudentId().getThesisId().getId();
        }
        else{
            return "thesis";
        }
    }
    
    @RequestMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }
    
}
