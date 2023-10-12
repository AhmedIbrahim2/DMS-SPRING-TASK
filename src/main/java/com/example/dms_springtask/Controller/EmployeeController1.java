package com.example.dms_springtask.Controller;


import com.example.dms_springtask.Dto.DepartmentDto;
import com.example.dms_springtask.Dto.EmployeeDto;
import com.example.dms_springtask.Exceptions.DuplicateCodeException;
import com.example.dms_springtask.Service.Department_serviceImp;
import com.example.dms_springtask.Service.Employee_serviceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Employee")
public class EmployeeController1 {


    @Autowired
    Employee_serviceImp employee_serviceImp;

    @Autowired
    Department_serviceImp department_serviceImp;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/getAllEmployee")
    public String employeePage(Model model){
        model.addAttribute("employees",employee_serviceImp.getAllEmployees());
        return "employee";
    }



    @GetMapping("/employeeRegister")
    public String Register(Model model){
        model.addAttribute("department",department_serviceImp.getAllDepartment());
        return "/RegisterEmployee";
    }


    @RequestMapping("/editEmployee/{employeeId}")
    public String editEmployee(@PathVariable("employeeId")Long employeeId ,Model model){
        EmployeeDto e = employee_serviceImp.getEmployeeById(employeeId);
        model.addAttribute("Employee",e);
        model.addAttribute("departments",department_serviceImp.getAllDepartment());
        return "/EditEmployee";
    }


    @PostMapping("/save")
    public String saveEmployees(@ModelAttribute EmployeeDto employeeDto, Model model) {
        try {
            if (employee_serviceImp.isCodeEmployeeDuplicated(employeeDto.getCodeEmployee())) {
                throw new DuplicateCodeException("Employee code already exists.");
            }
            employee_serviceImp.add(employeeDto);
            return "redirect:/Employee/getAllEmployee";
        } catch (DuplicateCodeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("departments", department_serviceImp.getAllDepartment());
            return "/RegisterEmployee";
        }
    }

    @PostMapping("/saves")
    public String saveEmployee(@ModelAttribute EmployeeDto employeeDto) {
        employee_serviceImp.edit(employeeDto.getCodeEmployee(),employeeDto);
        return "redirect:/Employee/getAllEmployee";

    }


    @GetMapping("/search")
    public String searchEmployees(@RequestParam(name = "name", required = false) String name,
                                  Model model) {
        List<EmployeeDto> employees;

        if (name != null && !name.isEmpty()) {
            employees = employee_serviceImp.searchByName(name);
        } else {
            employees = null;
        }

        model.addAttribute("employe", employees);
        return "employee"; // Replace with the actual name of your employee page HTML template
    }




    @RequestMapping("/deleteEmployee/{codeEmployee}")
    public String deleteEmployee(@PathVariable("codeEmployee") Long codeEmployee){
        employee_serviceImp.delete(codeEmployee);
        return "redirect:/Employee/getAllEmployee";
    }




}
