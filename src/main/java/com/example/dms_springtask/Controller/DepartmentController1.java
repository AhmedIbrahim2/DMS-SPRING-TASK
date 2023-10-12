package com.example.dms_springtask.Controller;


import com.example.dms_springtask.Dto.DepartmentDto;
import com.example.dms_springtask.Exceptions.DuplicateCodeException;
import com.example.dms_springtask.Service.DepartmentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Department")
public class DepartmentController1 {


    @Autowired
    DepartmentServiceImp depService;


    @GetMapping("/getAllDepartment")
    public String departmentPage(Model model){
        model.addAttribute("departments", depService.getAllDepartment());
        return "/department";
    }

    @GetMapping("/departmentRegister")
    public String Register(Model model){
        return "/registerDepartment";
    }


    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute DepartmentDto departmentDto, Model model) {
        try {
            if (depService.isCodeDuplicated(departmentDto.getCodeDepartment())) {
                throw new DuplicateCodeException("Department code already exists.");
            }
            depService.add(departmentDto);
            return "redirect:/Department/getAllDepartment";
        } catch (DuplicateCodeException e) {
            model.addAttribute("error", e.getMessage());
            return "/registerDepartment";
        }
    }


    @GetMapping("/search")
    public String searchDepartments(@RequestParam(name = "name", required = false) String name,
                                    Model model) {
        List<DepartmentDto> departments;

        if (name != null && !name.isEmpty()) {
            departments = depService.searchByName(name);
        } else {
            departments = depService.getAllDepartment();
        }

        model.addAttribute("departments", departments);
        return "department";
    }

    @PostMapping("/saves")
    public String saveDepartments(@ModelAttribute DepartmentDto departmentDto) {
            depService.edit(departmentDto.getDepartmentId(),departmentDto);
           return "redirect:/Department/getAllDepartment";

    }


    @RequestMapping("/editDepartment/{codeDepartment}")
    public String editDepartment(@PathVariable("codeDepartment")Long codeDepartment , Model model){
        DepartmentDto d = depService.getDepartmentById(codeDepartment);
        model.addAttribute("Department",d);
        return "/EditDepartment";
    }

    @RequestMapping("/deleteDepartment/{codeDepartment}")
    public String deleteDepartment(@PathVariable("codeDepartment") Long codeDepartment){
        depService.delete(codeDepartment);
        return "redirect:/Department/getAllDepartment";
    }





}
