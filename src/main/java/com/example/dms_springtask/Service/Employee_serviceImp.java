package com.example.dms_springtask.Service;


import com.example.dms_springtask.Dto.EmployeeDto;
import com.example.dms_springtask.Exceptions.DuplicateCodeException;
import com.example.dms_springtask.Exceptions.NotFoundException;
import com.example.dms_springtask.Model.Department;
import com.example.dms_springtask.Model.Employee;
import com.example.dms_springtask.Repository.Employee_Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Employee_serviceImp implements Employee_service {

    //get id done
    //get all data done
    // ? edit
    // ? save
    // ? delete

    @Autowired
    Employee_Repository employee_repository;

    @Override
    public EmployeeDto add(EmployeeDto employeeDto) {
//
//        Optional<Employee> existingEmployee = employee_repository.findById(employeeDto.getCodeEmployee());
//
//        if (existingEmployee.isPresent()){
//
//            throw new DuplicateCodeException("This Code Is Duplicated");
//        }else {

        if (isCodeEmployeeDuplicated(employeeDto.getCodeEmployee())) {
            throw new DuplicateCodeException("Employee code already exists.");
        }
            Employee newEmployee = EmployeeDto.dtoToEmployee(employeeDto);
            Employee savedEmployee = employee_repository.save(newEmployee);
            return EmployeeDto.employeeToDto(savedEmployee);
     //   }

    }

    @Override
    public String delete(Long code) {

        Optional<Employee> employee = employee_repository.findById(code);
        if (employee.isPresent()){
            employee_repository.deleteById(code);
            return "Deleted Successfully";
        }else {
            throw  new NotFoundException("Id Not Found");
        }
    }

    @Override
    public EmployeeDto edit(Long code, EmployeeDto employeeDto) {

        Optional<Employee> optionalEmployee = employee_repository.findById(code);

        if (optionalEmployee.isPresent()){
            Employee existingEmployee = optionalEmployee.get();
            BeanUtils.copyProperties(employeeDto,existingEmployee,"employeeId");
            employee_repository.save(existingEmployee);
            return EmployeeDto.employeeToDto(existingEmployee);
        }else {
            throw new NotFoundException("Id Not Found");
        }
    }

    public boolean isCodeEmployeeDuplicated(Long employeeId) {
        return employee_repository.existsByCodeEmployee(employeeId);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees =employee_repository.findAll();

       return employees
                 .stream()
                 .map(EmployeeDto::employeeToDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> searchByName(String name) {

        List<Employee> employees = employee_repository.findByName(name);

        // Convert entities to DTOs
        return employees.stream()
                .map(EmployeeDto::employeeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employee_repository.findById(employeeId);

        if (optionalEmployee.isPresent()){
            return EmployeeDto.employeeToDto(optionalEmployee.get());
        }else {
            return null;
        }

      //  return optionalEmployee.orElse(null); // Return null if the employee is not found
    }



}
