package com.example.dms_springtask.Service;


import com.example.dms_springtask.Dto.DepartmentDto;
import com.example.dms_springtask.Exceptions.DuplicateCodeException;
import com.example.dms_springtask.Exceptions.NotFoundException;
import com.example.dms_springtask.Model.Department;
import com.example.dms_springtask.Model.Employee;
import com.example.dms_springtask.Repository.Department_Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Department_serviceImp implements Department_service {

    //get all
    // get id
    // save
    //edit  only
    // delete

    @Autowired
    Department_Repository department_repository;

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departmentList = department_repository.findAll();

        return departmentList.
                stream()
                .map(DepartmentDto::departmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {

        Optional<Department> department = department_repository.findById(departmentId);

        if (department.isPresent()) {
            return DepartmentDto.departmentToDto(department.get());
        } else {
            return null;
        }
        //   return department.orElse(null);

    }


    @Override
    public DepartmentDto add(DepartmentDto department) {
     //   Optional<Department> optionalDepartment = department_repository.findById(department.getCodeDepartment());

//        if (optionalDepartment.isPresent()) {
//            throw new DuplicateCodeException("Department code already exists.");
//
//        } else {

        if (isCodeDuplicated(department.getCodeDepartment())) {
            throw new DuplicateCodeException("Department code already exists.");
        }
            Department existingDepartment = DepartmentDto.dtoToDepartment(department);
            existingDepartment.setCode(department.getCodeDepartment());
            Department savedDepartment = department_repository.save(existingDepartment);
            return DepartmentDto.departmentToDto(savedDepartment);
//        }
    }

    @Override
    public String delete(Long departmentId) {
        Optional<Department> optionalDepartment = department_repository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            department_repository.deleteById(departmentId);
            return "Deleted Successfully";
        } else {
            return null;
        }
    }

    public boolean isCodeDuplicated(Long codeDepartment) {
        return department_repository.existsByCodeDepartment(codeDepartment);
    }

    @Override
    public DepartmentDto edit(Long code, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = department_repository.findById(code);



        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();

            Long newCode = departmentDto.getCodeDepartment();
            Optional<Department> existingDepartmentWithNewCode = department_repository.findById(newCode);

            if (existingDepartmentWithNewCode.isPresent() && existingDepartmentWithNewCode.get().getCode() == departmentDto.getCodeDepartment()) {
                throw new DuplicateCodeException("Code already exists for another department");
            }
            BeanUtils.copyProperties(departmentDto, existingDepartment, "departmentId");
            existingDepartment.setCode(departmentDto.getCodeDepartment());
            department_repository.save(existingDepartment);
            return DepartmentDto.departmentToDto(existingDepartment);
        } else {
            throw new NotFoundException("Department Not Found");
        }

    }


}