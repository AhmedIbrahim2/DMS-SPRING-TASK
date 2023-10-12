package com.example.dms_springtask.Repository;

import com.example.dms_springtask.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    boolean existsByCodeDepartment(Long departmentId);
    List<Department> findByNameContainingIgnoreCase(String name);

}
