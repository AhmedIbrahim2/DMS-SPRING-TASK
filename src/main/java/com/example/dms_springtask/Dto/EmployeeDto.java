package com.example.dms_springtask.Dto;

import com.example.dms_springtask.Model.Department;
import com.example.dms_springtask.Model.Employee;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeDto{

    private Long codeEmployee;

    private String name;

    private LocalDate birthDate;

    private String address;

    private String phone ;

    private double salary ;

    private String departmentName ;

    private Department department;

    public static EmployeeDto employeeToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setCodeEmployee(employee.getCodeEmployee());
        employeeDto.setName(employee.getName());
        employeeDto.setBirthDate(employee.getBirthDate());
        employeeDto.setAddress(employee.getAddress());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setSalary(employee.getSalary());

        if (employee.getDepartment() != null) {
            employeeDto.setDepartment(employee.getDepartment());
            employeeDto.setDepartmentName(employee.getDepartment().getName());
        }
        return employeeDto;
    }

    public static Employee dtoToEmployee (EmployeeDto employeeDto){

        Employee employee = new Employee();
        employee.setCodeEmployee(employeeDto.getCodeEmployee());
        employee.setName(employeeDto.getName());
        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setAddress(employeeDto.getAddress());
        employee.setPhone(employeeDto.getPhone());
        employee.setSalary(employeeDto.getSalary());
        employee.setDepartment(employeeDto.getDepartment());
        return employee;
    }



}