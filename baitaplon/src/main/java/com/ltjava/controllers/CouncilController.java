/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.pojo.Council;
import com.ltjava.pojo.Criteria;
import com.ltjava.pojo.Teacher;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisScore;
import com.ltjava.service.CouncilMemberService;
import com.ltjava.service.CouncilService;
import com.ltjava.service.CriteriaService;
import com.ltjava.service.MemberRoleService;
import com.ltjava.service.TeacherService;
import com.ltjava.service.ThesisScoreService;
import com.ltjava.service.ThesisService;
import com.ltjava.service.UserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HIEN
 */
@Controller
public class CouncilController {
    @Autowired
    private CouncilService councilService;
    
    @Autowired
    private CouncilMemberService councilMemberService;
    
    @Autowired
    private CriteriaService criteriaService;
    
    @Autowired
    private ThesisScoreService thesisScoreService;
    
    @Autowired
    private ThesisService thesisService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private MemberRoleService memberRoleService;
    
    private Set<Thesis> thesises = new HashSet<>();
        
    @ModelAttribute
    public void commonAttr(Model model){
        model.addAttribute("listCouncil", councilMemberService.getListCouncilMember(councilService.getCouncils("")));
        model.addAttribute("listCriteria", criteriaService.getCriterias(""));
        model.addAttribute("listTeacher", teacherService.getTeachers("GV"));
        model.addAttribute("listMemberRole", memberRoleService.getMemberRoles());
    }
    
    @RequestMapping("/council")
    public String council(Model model){
        model.addAttribute("councilInfo", new Council());
        for(Teacher t : teacherService.getTeachers("GV")){
            
            System.out.println(t.getId());
        }
        return "council";
    }
    
    @PostMapping(value = "/council")
    public String addCouncil(@ModelAttribute(value = "councilInfo") Council councilInfo){
        if(councilService.addCouncil(councilInfo)){
            return "redirect:/council";
        }
        return "council";
    }
    
    @GetMapping(value = "/council/{councilId}")
    public String councilItem(Model model, @PathVariable(value = "councilId") Integer Id){
        model.addAttribute("councilPage", councilService.getCouncilById(Id));
        model.addAttribute("listThesises", this.thesisService.getThesises(""));
        return "councilitem";
    }
    
    @PostMapping(value = "/council/{councilId}")
    public String councilItemAddThesis(Model model, @PathVariable(value = "councilId") Integer councilId, @RequestParam(value = "addThesisId") Integer thesisId){
        try{
            Thesis thes = this.thesisService.getThesisById(thesisId);
            thes.setCouncilId(this.councilService.getCouncilById(councilId));
            if(this.thesisService.addOrUpdateThesis(thes)){
                model.addAttribute("msg", "Thêm khóa luận thành công");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("msg", "Đã xảy ra lỗii");
        }
        model.addAttribute("councilPage", councilService.getCouncilById(councilId));
        model.addAttribute("listThesises", this.thesisService.getThesises(""));
        return "councilitem";
    }
    
    @RequestMapping("/council/criteria")
    public String criteria(Model model, @RequestParam(value = "councilId", required = false, defaultValue = "") Integer councilId){
        if(councilId!=null){
            try{
                thesises = (councilService.getCouncilById(councilId).getThesises());
                for (Thesis thesis:thesises){
                    System.out.println(thesis.getTopic());
                }
            }
            catch(Exception ex){
                System.out.println("LỖI RỒIIIII");
                System.out.println(ex.getMessage());
                System.out.println(ex.getStackTrace());
            }
        }
//        List<Object[]> list = new ArrayList<>();
//        list = thesisScoreService.getThesisScores(1);
//        for (Object[] objects : list) {
//            System.out.println("Mã khóa luận: "+ objects[0]);
//            System.out.println("Chủ đề: "+ objects[1]);
//            System.out.println("Tiêu chí: "+objects[2]);
//            System.out.println("Điểm: "+objects[3]);
//            System.out.println("Người chấm: "+objects[4]);
//            System.out.println("Thời gian chấm: "+ objects[5]);
//        }
        model.addAttribute("thesisScoreInfo", new ThesisScore());
        model.addAttribute("criteriaInfo", new Criteria());
        model.addAttribute("listThesis", thesises);
        return "criteria";
    }
    
    @PostMapping("/council/criteria")
    public String addCriteria(Model model, @ModelAttribute(value = "criteriaInfo") Criteria c){
        System.out.print("post");
        if(criteriaService.addCriteria(c)){
            return "redirect:/council/criteria";
        }
        return "criteria";
    }
    
    @RequestMapping("/council/criteria/{thesisId}")
    public String thesisScore(Model model, @PathVariable("thesisId") Integer thesisId){
        System.out.println(thesisId);
        model.addAttribute("thesisScore", thesisService.getThesisById(thesisId));
        return "thesisScore";
    }
    
    @RequestMapping("/council/lock/{councilId}")
    public String lock(Model model, @PathVariable("councilId") Integer councilId){
        if(councilService.getCouncilById(councilId)!=null){
            councilService.lockCouncil(councilService.getCouncilById(councilId));
        }
        return "redirect:/council";
    }
    
    @RequestMapping("/council/unlock/{councilId}")
    public String unlock(Model model, @PathVariable("councilId") Integer councilId){
        if(councilService.getCouncilById(councilId)!=null){
            councilService.unlockCouncil(councilService.getCouncilById(councilId));
        }
        return "redirect:/council";
    }
}
