package com.example.dms_springtask.Service;

import com.example.dms_springtask.Dto.DepartmentDto;
import com.example.dms_springtask.Model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Department_service {

    public DepartmentDto add(DepartmentDto department);

    public String delete(Long departmentId);

    public DepartmentDto edit(Long departmentId , DepartmentDto department);

    public List<DepartmentDto> getAllDepartment();

    public DepartmentDto getDepartmentById(Long departmentId);
}
