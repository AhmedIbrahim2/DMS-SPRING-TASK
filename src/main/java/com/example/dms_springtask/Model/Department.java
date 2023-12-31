package com.example.dms_springtask.Model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public
class Department{





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    public Long getCodeDepartment() {
        return codeDepartment;
    }

    public void setCodeDepartment(Long codeDepartment) {
        this.codeDepartment = codeDepartment;
    }

    private Long codeDepartment ;
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    private String name ;

    @Column(nullable = false)
   private String description;

    @OneToMany(mappedBy = "department")
    List<Employee> employeeList;

    public Department(){}
    public Department(Long codeDepartment, Long departmentId, String name, String description, List<Employee> employeeList) {
        this.codeDepartment = codeDepartment;
        this.departmentId = departmentId;
        this.name = name;
        this.description = description;
        this.employeeList = employeeList;
    }




    public Long getCode() {
        return codeDepartment;
    }

    public void setCode(Long code) {
        this.codeDepartment = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}