package com.example.dms_springtask.Service;

import com.example.dms_springtask.Dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public EmployeeDto add(EmployeeDto employee);

    public String delete(Long code);

    public EmployeeDto edit(Long code , EmployeeDto employee);

    public List<EmployeeDto> getAllEmployees();

    public EmployeeDto getEmployeeById(Long code);

}
