package com.example.dms_springtask.Repository;

import com.example.dms_springtask.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Department_Repository extends JpaRepository<Department,Long> {
    boolean existsByCodeDepartment(Long departmentId);
}
