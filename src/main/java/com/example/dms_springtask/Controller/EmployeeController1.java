package com.example.dms_springtask.Controller;


import com.example.dms_springtask.Dto.EmployeeDto;
import com.example.dms_springtask.Exceptions.DuplicateCodeException;
import com.example.dms_springtask.Service.DepartmentServiceImp;
import com.example.dms_springtask.Service.EmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Employee")
public class EmployeeController1 {


    @Autowired
    EmployeeServiceImp employeeServiceImp;

    @Autowired
    DepartmentServiceImp departmentServiceImp;


    @GetMapping("/getAllEmployee")
    public String employeePage(Model model){
        model.addAttribute("employees", employeeServiceImp.getAllEmployees());
        return "employee";
    }



    @GetMapping("/employeeRegister")
    public String Register(Model model){
        model.addAttribute("department", departmentServiceImp.getAllDepartment());
        return "/RegisterEmployee";
    }


    @RequestMapping("/editEmployee/{employeeId}")
    public String editEmployee(@PathVariable("employeeId")Long employeeId ,Model model){
        EmployeeDto e = employeeServiceImp.getEmployeeById(employeeId);
        model.addAttribute("Employee",e);
        model.addAttribute("departments", departmentServiceImp.getAllDepartment());
        return "/EditEmployee";
    }


    @PostMapping("/save")
    public String saveEmployees(@ModelAttribute EmployeeDto employeeDto, Model model) {
        try {
            if (employeeServiceImp.isCodeEmployeeDuplicated(employeeDto.getCodeEmployee())) {
                throw new DuplicateCodeException("Employee code already exists.");
            }
            employeeServiceImp.add(employeeDto);
            return "redirect:/Employee/getAllEmployee";
        } catch (DuplicateCodeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("departments", departmentServiceImp.getAllDepartment());
            return "/RegisterEmployee";
        }
    }

    @PostMapping("/saves")
    public String saveEmployee(@ModelAttribute EmployeeDto employeeDto , Model model) {
                employeeServiceImp.edit(employeeDto.getEmployeeId(), employeeDto);
                return "redirect:/Employee/getAllEmployee";


    }




    @GetMapping("/search")
    public String searchEmployees(@RequestParam(name = "name", required = false) String name,
                                  Model model) {
        List<EmployeeDto> employees;

        if (name != null && !name.isEmpty()) {
            employees = employeeServiceImp.searchByName(name);
        } else {
            employees = employeeServiceImp.getAllEmployees();
        }

        model.addAttribute("employees", employees);
        return "employee"; // Replace with the actual name of your employee page HTML template
    }




    @RequestMapping("/deleteEmployee/{codeEmployee}")
    public String deleteEmployee(@PathVariable("codeEmployee") Long codeEmployee){
        employeeServiceImp.delete(codeEmployee);
        return "redirect:/Employee/getAllEmployee";
    }




}
