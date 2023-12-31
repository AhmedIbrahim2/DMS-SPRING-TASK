package com.example.dms_springtask.Repository;


import com.example.dms_springtask.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {
    boolean existsByCodeEmployee(Long codeEmployee);
    List<Employee> findByName(String name);}
