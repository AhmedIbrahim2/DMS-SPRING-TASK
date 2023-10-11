package com.example.dms_springtask.Controller;


import com.example.dms_springtask.Dto.DepartmentDto;
import com.example.dms_springtask.Dto.EmployeeDto;
import com.example.dms_springtask.Exceptions.DuplicateCodeException;
import com.example.dms_springtask.Service.Department_serviceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Department")
public class DepartmentController1 {


    @Autowired
    Department_serviceImp department_serviceImp;


    @GetMapping("/getAllDepartment")
    public String departmentPage(Model model){
        model.addAttribute("departments",department_serviceImp.getAllDepartment());
        return "/department";
    }

    @GetMapping("/departmentRegister")
    public String Register(Model model){
        return "/registerDepartment";
    }


    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute DepartmentDto departmentDto, Model model) {
        try {
            if (department_serviceImp.isCodeDuplicated(departmentDto.getCodeDepartment())) {
                throw new DuplicateCodeException("Department code already exists.");
            }
            department_serviceImp.add(departmentDto);
            return "redirect:/Department/getAllDepartment";
        } catch (DuplicateCodeException e) {
            model.addAttribute("error", e.getMessage());
            return "/registerDepartment";
        }
    }

    @PostMapping("/saves")
    public String saveDepartments(@ModelAttribute DepartmentDto departmentDto) {
            department_serviceImp.edit(departmentDto.getDepartmentId(),departmentDto);
           return "redirect:/Department/getAllDepartment";

    }


    @RequestMapping("/editDepartment/{codeDepartment}")
    public String editDepartment(@PathVariable("codeDepartment")Long codeDepartment , Model model){
        DepartmentDto d = department_serviceImp.getDepartmentById(codeDepartment);
        model.addAttribute("Department",d);
        return "/EditDepartment";
    }

    @RequestMapping("/deleteDepartment/{codeDepartment}")
    public String deleteDepartment(@PathVariable("codeDepartment") Long codeDepartment){
        department_serviceImp.delete(codeDepartment);
        return "redirect:/Department/getAllDepartment";
    }





}
