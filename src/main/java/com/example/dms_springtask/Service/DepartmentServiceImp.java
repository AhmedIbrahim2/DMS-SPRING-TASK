package com.example.dms_springtask.Service;


import com.example.dms_springtask.Dto.DepartmentDto;
import com.example.dms_springtask.Exceptions.DuplicateCodeException;
import com.example.dms_springtask.Exceptions.NotFoundException;
import com.example.dms_springtask.Model.Department;
import com.example.dms_springtask.Repository.DepartmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departmentList = departmentRepository.findAll();

        return departmentList.
                stream()
                .map(DepartmentDto::departmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {

        Optional<Department> department = departmentRepository.findById(departmentId);

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
            Department savedDepartment = departmentRepository.save(existingDepartment);
            return DepartmentDto.departmentToDto(savedDepartment);
//        }
    }

    @Override
    public String delete(Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            departmentRepository.deleteById(departmentId);
            return "Deleted Successfully";
        } else {
            return null;
        }
    }

    public boolean isCodeDuplicated(Long codeDepartment) {
        return departmentRepository.existsByCodeDepartment(codeDepartment);
    }

    @Override
    public DepartmentDto edit(Long id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
          // exist : true
          // new code :
          // compare with exist


        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();

            if (existingDepartment.getCodeDepartment() != departmentDto.getCodeDepartment()){
                Optional<Department> department = departmentRepository.findByCodeDepartment(departmentDto.getCodeDepartment());

                if (department.isPresent()){

                    throw new DuplicateCodeException("this code is Duplicated");
                }

            }



            BeanUtils.copyProperties(departmentDto, existingDepartment, "departmentId");
            existingDepartment.setCode(departmentDto.getCodeDepartment());
            departmentRepository.save(existingDepartment);
            return DepartmentDto.departmentToDto(existingDepartment);
        } else {
            throw new NotFoundException("Department Not Found");
        }

    }


    public List<DepartmentDto> searchByName(String name) {
        List<Department> departmentEntities = departmentRepository.findByNameContainingIgnoreCase(name);
        return departmentEntities.stream().map(DepartmentDto::departmentToDto).collect(Collectors.toList());
    }

    public List<DepartmentDto> searchByNameAndDescription(String name , String description) {
        List<Department> departmentEntities = departmentRepository.findByNameAndDescriptionContainingIgnoreCase(name,description);
        return departmentEntities.stream().map(DepartmentDto::departmentToDto).collect(Collectors.toList());
    }

    public List<DepartmentDto> searchByDescription(String description) {
        List<Department> departmentEntities = departmentRepository.findByDescriptionContainingIgnoreCase(description);
        return departmentEntities.stream().map(DepartmentDto::departmentToDto).collect(Collectors.toList());
    }


}