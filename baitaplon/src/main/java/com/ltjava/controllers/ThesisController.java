/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisCriteria;
import com.ltjava.pojo.ThesisInstructor;
import com.ltjava.pojo.ThesisScore;
import com.ltjava.pojo.User;
import com.ltjava.service.CouncilService;
import com.ltjava.service.StudentService;
import com.ltjava.service.ThesisCriteriaService;
import com.ltjava.service.ThesisInstructorService;
import com.ltjava.service.ThesisScoreService;
import com.ltjava.service.ThesisService;
import com.ltjava.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HIEN
 */
@Controller
@ControllerAdvice
public class ThesisController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private ThesisService thesisService;
    
    @Autowired
    private ThesisInstructorService thesisInstructorService;
    
    @Autowired
    private ThesisScoreService thesisScoreService;
    
    @Autowired
    private ThesisCriteriaService thesisCriteriaService;
    
    @Autowired
    private CouncilService councilService;
    
    public List<Student> thesisStudents = new ArrayList<>(); 
    
    public List<User> thesisTeachers = new ArrayList<>(); 
    
    @ModelAttribute
    public void commonAttr(Model model){
        model.addAttribute("listThesis", this.thesisService.getThesises(""));
        model.addAttribute("listTeacher", this.userService.getUsers("", "TEACHER"));
        model.addAttribute("listStudent", this.studentService.getStudents(new HashMap<String,String>()));
    }
    
    @RequestMapping("/thesis")
    public String thesis(Model model){
        return "thesis";
    }
    
    @PostMapping(value = "/thesis/addThesis")
    public String thesis(@ModelAttribute(value = "thesisInfo") Thesis thesisInfo){
        if(thesisService.addOrUpdateThesis(thesisInfo)){
            if(thesisStudents.size()>0){
                for (Student thesisStudent : thesisStudents) {
                    studentService.addThesis(thesisStudent, thesisInfo);
                }
            }
            thesisStudents.clear();
            if(thesisTeachers.size()>0){
                for (User thesisTeacher : thesisTeachers) {
                    thesisInstructorService.addOrUpdateThesisInstructor(new ThesisInstructor(thesisInfo, thesisTeacher));
                }
            }
            thesisStudents.clear();
            thesisTeachers.clear();
            return "redirect:/thesis";
        }
        return "addThesis";
    }
    
    @RequestMapping("/thesis/reviewer")
    public String reviewer(Model model){
        return "reviewer";
    }
    
    @RequestMapping("/thesis/addThesis")
    public String addThesis(Model model, @RequestParam(value="studentId", required = false, defaultValue = "") String studentId,
            @RequestParam(value="removeId", required = false, defaultValue = "") String removeId,
            @RequestParam(value="teacherId", required = false, defaultValue = "") String teacherId,
            @RequestParam(value="removeTId", required = false, defaultValue = "") String removeTId){
        model.addAttribute("thesisInfo", new Thesis());
        if(studentService.getStudentById(studentId)!=null){
            thesisStudents.add(studentService.getStudentById(studentId));
            System.out.println(thesisStudents.size());
        }
        if(!removeId.isEmpty()&&removeId!=""&&thesisStudents.size()>0){
            for (int i =0; i<thesisStudents.size();i++){
                if(thesisStudents.get(i).getId().equals(removeId)){
                    thesisStudents.remove(i);
                }
            }
            System.out.println(thesisStudents.size());
        }
        if(userService.getUserById(teacherId)!=null){
            thesisTeachers.add(userService.getUserById(teacherId));
            System.out.println(thesisTeachers.size());
        }
        if(!removeTId.isEmpty()&&removeTId!=""&&thesisTeachers.size()>0){
            for (int i =0; i<thesisTeachers.size();i++){
                if(thesisTeachers.get(i).getId().equals(removeTId)){
                    thesisTeachers.remove(i);
                }
            }
            System.out.println(thesisTeachers.size());
        }
        model.addAttribute("students", thesisStudents);
        model.addAttribute("teachers", thesisTeachers);
        return "addThesis";
    }
    
    @RequestMapping("/thesis/{thesisId}")
    public String thesisItem(Model model, @PathVariable(value = "thesisId") Integer thesisId){
        model.addAttribute("thesisPage", thesisService.getThesisById(thesisId));
        return "thesisItem";
    }
    
    @RequestMapping("/thesis/remove/{thesisId}")
    public String removeThesis(Model model, @PathVariable(value = "thesisId") Integer thesisId){
        try{
            Thesis thesis = thesisService.getThesisById(thesisId);
            if(thesis!=null){
                if(thesisInstructorService.removeThesisInstructors(thesis))
                    thesisService.removeThesis(thesisService.getThesisById(thesisId));
            }
            return "redirect:/thesis";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
    
    
    @RequestMapping("/thesis/score/{user}/{councilId}")
    public String score(Model model, @PathVariable(value = "user") String u, @PathVariable(value = "councilId") Integer councilId){
        model.addAttribute("user", this.userService.getUserById(u));
        model.addAttribute("council", this.councilService.getCouncilById(councilId));
        return "score";
    }
    
    @PostMapping("/thesis/score/{user}/addScore/{thesisCriteriaId}")
    public String addScore(Model model,@PathVariable(value = "user") String userId,
            @PathVariable(value = "thesisCriteriaId") Integer thesisCriteriaId,
            @RequestParam(value="score") Float score){
        try{
            ThesisCriteria thesisC = this.thesisCriteriaService.getThesisCriteriaById(thesisCriteriaId);
            User user = this.userService.getUserById(userId);
            ThesisScore ts = new ThesisScore(score, thesisC, user);
            if(thesisScoreService.addOrUpdate(ts)){
                return "redirect:/thesis/score/"+userId+"/"+thesisC.getThesisId().getCouncilId().getId();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "reditect:/";
    }
    
    @PostMapping("/thesis/score/{user}/editScore/{thesisCriteriaId}")
    public String editScore(Model model,@PathVariable(value = "user") String userId,
            @PathVariable(value = "thesisCriteriaId") Integer thesisCriteriaId,
            @RequestParam(value="score") Float score){
        try{
            ThesisScore ts = this.thesisScoreService.getThesisScoreByUTC(thesisCriteriaId, userId);
            ts.setScore(score);
            if(thesisScoreService.addOrUpdate(ts)){
                return "redirect:/thesis/score/"+userId+"/"+ts.getThesisCriteriaId().getThesisId().getCouncilId().getId();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "reditect:/";
    }
}
