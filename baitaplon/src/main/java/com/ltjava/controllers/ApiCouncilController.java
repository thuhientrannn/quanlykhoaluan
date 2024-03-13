/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.pojo.Council;
import com.ltjava.pojo.CouncilMember;
import com.ltjava.pojo.Criteria;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisCriteria;
import com.ltjava.pojo.User;
import com.ltjava.service.CouncilMemberService;
import com.ltjava.service.CouncilService;
import com.ltjava.service.CriteriaService;
import com.ltjava.service.MemberRoleService;
import com.ltjava.service.ThesisCriteriaService;
import com.ltjava.service.ThesisService;
import com.ltjava.service.UserService;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HIEN
 */
@RestController
public class ApiCouncilController {
    @Autowired
    private CouncilService councilService;
    
    @Autowired 
    private UserService userService;
    
    @Autowired
    private CouncilMemberService councilMemberService;
    
    @Autowired
    private MemberRoleService memberRoleService;
    
    @Autowired
    private ThesisService thesisService;
    
    @Autowired
    private CriteriaService criteriaService;
    
    @Autowired
    private ThesisCriteriaService thesisCriteriaService;
    
    @PostMapping(path = "/api/council/add", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Council> addCouncil(@RequestBody Map<String,String> params){
        try{
            Council council = new Council(params.get("name"));
            User president = userService.getUserById(params.get("presidentId"));
            User secretary = userService.getUserById(params.get("secretaryId"));
            User reviewer = userService.getUserById(params.get("reviewerId"));
            if(councilService.addCouncil(council)){
                this.councilMemberService.addCouncilMember(new CouncilMember(council, president, memberRoleService.getMemberRoleById(1)));
                this.councilMemberService.addCouncilMember(new CouncilMember(council, secretary, memberRoleService.getMemberRoleById(2)));
                this.councilMemberService.addCouncilMember(new CouncilMember(council, reviewer, memberRoleService.getMemberRoleById(3)));
                if(userService.getUserById(params.get("member1Id"))!=null){
                    this.councilMemberService.addCouncilMember(new CouncilMember(council,userService.getUserById(params.get("member1Id")) , memberRoleService.getMemberRoleById(4)));
                }
                if(userService.getUserById(params.get("member2Id"))!=null){
                    this.councilMemberService.addCouncilMember(new CouncilMember(council,userService.getUserById(params.get("member2Id")) , memberRoleService.getMemberRoleById(4)));
                }
                System.out.println("THÊM DANH SÁCH THÀNH VIÊN THÀNH CÔNG");
            }
            else{
                System.out.println("THÊM HỘI ĐỒNG THẤT BẠI");
            }
            return new ResponseEntity<>(council, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path = "/api/council/edit", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Council> editCouncil(@RequestBody Map<String,String> params){
        try{
            Council council = councilService.getCouncilById(Integer.parseInt(params.get("councilId")));
            User president = userService.getUserById(params.get("presidentId"));
            User secretary = userService.getUserById(params.get("secretaryId"));
            User reviewer = userService.getUserById(params.get("reviewerId"));
            if(councilService.removeCouncilMembers(council)){
                this.councilMemberService.addCouncilMember(new CouncilMember(council, president, memberRoleService.getMemberRoleById(1)));
                this.councilMemberService.addCouncilMember(new CouncilMember(council, secretary, memberRoleService.getMemberRoleById(2)));
                this.councilMemberService.addCouncilMember(new CouncilMember(council, reviewer, memberRoleService.getMemberRoleById(3)));
                if(userService.getUserById(params.get("member1Id"))!=null){
                    this.councilMemberService.addCouncilMember(new CouncilMember(council,userService.getUserById(params.get("member1Id")) , memberRoleService.getMemberRoleById(4)));
                }
                if(userService.getUserById(params.get("member2Id"))!=null){
                    this.councilMemberService.addCouncilMember(new CouncilMember(council,userService.getUserById(params.get("member2Id")) , memberRoleService.getMemberRoleById(4)));
                }
                System.out.println("SỬA DANH SÁCH THÀNH VIÊN THÀNH CÔNG");
            }
            else{
                System.out.println("SỬA DANH SÁCH THÀNH VIÊN THẤT BẠI");
            }
            return new ResponseEntity<>(council, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping(path = "/api/council/thesis", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Thesis> getThesis(@RequestBody Map<String,String> params){
        try{
            Thesis thesis = this.thesisService.getThesisById(Integer.parseInt(params.get("thesisId")));
            return new ResponseEntity<>(thesis, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path = "/api/council/addThesisCriteria", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<ThesisCriteria> addThesisCriteria(@RequestBody Map<String,String> params){
        try{
            Thesis thesis = this.thesisService.getThesisById(Integer.parseInt(params.get("thesisId")));
            Criteria criteria = this.criteriaService.getCriteriaByName(params.get("criteriaName"));
            Float maxSCore = Float.valueOf(params.get("maxScore"));
            if(criteria==null){
                criteria = new Criteria(params.get("criteriaName"));
                this.criteriaService.addCriteria(criteria);
            }
            ThesisCriteria thesisCriteria = new ThesisCriteria(thesis, criteria, maxSCore);
            if(this.thesisCriteriaService.addOrUpdate(thesisCriteria)){
                return new ResponseEntity<>(thesisCriteria, HttpStatus.OK);
            }
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}