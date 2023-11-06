package com.example.dms_springtask.Repository;

import com.example.dms_springtask.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    boolean existsByCodeDepartment(Long departmentId);
    Optional<Department> findByCodeDepartment(Long codeDepartment);
    List<Department> findByNameContainingIgnoreCase(String name);

    List<Department> findByNameAndDescriptionContainingIgnoreCase(String name,String description);

    List<Department> findByDescriptionContainingIgnoreCase(String description);



}
