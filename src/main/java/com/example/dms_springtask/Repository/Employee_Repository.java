package com.example.dms_springtask.Repository;


import com.example.dms_springtask.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Employee_Repository extends JpaRepository<Employee , Long> {
    boolean existsByCodeEmployee(Long codeEmployee);
}
