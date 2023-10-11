package com.example.dms_springtask.Service;

import com.example.dms_springtask.Dto.EmployeeDto;
import com.example.dms_springtask.Model.Department;
import com.example.dms_springtask.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Employee_service {

    public EmployeeDto add(EmployeeDto employee);

    public String delete(Long code);

    public EmployeeDto edit(Long code , EmployeeDto employee);

    public List<EmployeeDto> getAllEmployees();

    public EmployeeDto getEmployeeById(Long code);

}
