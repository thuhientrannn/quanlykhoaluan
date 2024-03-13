/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lowagie.text.DocumentException;
import com.ltjava.baitaplon.PDFExporter;
import com.ltjava.pojo.Major;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.User;
import com.ltjava.service.ClassService;
import com.ltjava.service.MajorService;
import com.ltjava.service.StudentService;
import com.ltjava.service.ThesisScoreService;
import com.ltjava.service.ThesisService;
import com.ltjava.service.UserService;
import com.ltjava.service.YearService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HIEN
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private ClassService classService;
    
    @Autowired
    private MajorService majorService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ThesisService thesisService;
    
    @Autowired
    private ThesisScoreService thesisScoreService;
    
    @Autowired
    private YearService yearService;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @ModelAttribute
    public void commonAttr(Model model){
        model.addAttribute("listMajor", this.majorService.getMajors(""));
        model.addAttribute("listYear", this.yearService.getYears(""));
        model.addAttribute("listClass", this.classService.getClasses(""));
        model.addAttribute("newStudentId", this.studentService.loadNewStudentId());
    }
    
    @RequestMapping("/student")
    public String student(Model model,
            @RequestParam Map<String,String> params){
        model.addAttribute("studentInfo", new Student());
        model.addAttribute("listStudent", studentService.getStudents(params));
        return "student";
    }
    
    @PostMapping(value = "/student")
    public String addStudent(@ModelAttribute(value = "studentInfo")Student studentInfo){
        if(this.studentService.addOrUpdateStudent(studentInfo)){
            return "redirect:/student";
        }
        return "/";
    }
    
    @RequestMapping("/student/class")
    public String studentClass(Model model, 
            @RequestParam(value = "kw", required = false, defaultValue ="") String kw){
        model.addAttribute("classInfo", new com.ltjava.pojo.Class());
        model.addAttribute("listClass", classService.getClasses(kw));
        return "class";
    }
    
    @PostMapping(value = "/student/class")
    public String addClass(@ModelAttribute(value = "classInfo") com.ltjava.pojo.Class classInfo){
        if(this.classService.addClass(classInfo)){
            return "redirect:/student/class";
        }
        return "/";
    }
    @GetMapping(value = "/student/class/remove/{classId}")
    public String removeClass(@PathVariable(value = "classId") Integer id){
        try{
            classService.removeClass(classService.getClassById(id));
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        return "redirect:/student/class";
    }
    
    @RequestMapping("/student/major")
    public String studentMajor(Model model, 
            @RequestParam(value = "kw", required = false, defaultValue ="") String kw){
        model.addAttribute("majorInfo", new Major());
        model.addAttribute("listMajor", majorService.getMajors(kw));
        return "major";
    }
    
    @PostMapping(value = "/student/major")
    public String addMajor(@ModelAttribute(value = "majorInfo") Major majorInfo){ 
        System.out.print(majorInfo.getName());
        if(this.majorService.addMajor(majorInfo)){
            return "redirect:/student/major";
        }
        return "/";
    }
    
    @GetMapping(value = "/student/major/remove/{majorId}")
    public String removeMajor(@PathVariable(value = "majorId") Integer id){
        try{
            majorService.removeMajor(majorService.getMajorById(id));
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        return "redirect:/student/major";
    }
    
    @GetMapping("/student/thesisInfo/{thesisId}")
    public String thesisInfo(Model model,@PathVariable(value = "thesisId") Integer thesisId, 
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            @RequestParam(value = "fileUrl", required =false, defaultValue = "") String fileUrl){
        Thesis t = this.thesisService.getThesisById(thesisId); 
        model.addAttribute("thesisS", new Thesis());
        model.addAttribute("thesisInfo", this.thesisService.getThesisById(thesisId));
        try{
            if(!description.isEmpty()&&description!=""){                      
                t.setDescription(description);
            }
            if(!fileUrl.isEmpty()&&fileUrl!=""){                      
                t.setFileUrl(fileUrl);
            }
            if((!description.isEmpty()&&fileUrl!="")||(!fileUrl.isEmpty()&&description!="")){
                if(this.thesisService.addOrUpdateThesis(t)){
                    model.addAttribute("msgSuccess", "CẬP NHẬT THÀNH CÔNG");
                }
            }
        }catch(Exception e){
            System.out.println("== UPDATE DESCRIPTION ==" + e.getMessage());
            model.addAttribute("msgErr", "CẬP NHẬT THẤT BẠI");
        }
        
        return "studentThesis";
    }
    
//    @PostMapping("/student/thesisInfo/{thesisId}")
//    public String addFileThesis(Model model,@PathVariable(value = "thesisId") Integer thesisId, @ModelAttribute(value = "thesisS") Thesis thesis){
//        try {
//            Thesis t = this.thesisService.getThesisById(thesisId);
//            Map r = cloudinary.uploader().upload(thesis.getFile().getBytes(),
//            ObjectUtils.asMap("resource_type", "auto"));
//            String fileUrl = (String) r.get("secure_url");
//            t.setFileUrl(fileUrl);
//            if(this.thesisService.addOrUpdateThesis(t)){
//                model.addAttribute("msgSuccess", "THÊM FILE THÀNH CÔNG");
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//            model.addAttribute("msgErr", "THÊM FILE THẤT BẠI");
//        }
//        return "redirect:/student/thesisInfo/"+thesisId;
//    }
    
    @GetMapping("/export/pdf/{studentId}/{thesisId}")
    public void exportToPDF(HttpServletResponse response, @PathVariable(value = "studentId") String studentId, @PathVariable(value = "thesisId") Integer thesisId) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Thesis thesis = this.thesisService.getThesisById(thesisId);
        Student student = this.studentService.getStudentById(studentId);
         
        PDFExporter exporter = new PDFExporter(thesis,
                this.thesisScoreService.getListAvgScoreOfCriteria(thesisId), student);
        exporter.export(response);
    }
}
