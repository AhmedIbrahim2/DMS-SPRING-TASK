package com.example.dms_springtask.Model;


import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
public class Employee {



    private Long codeEmployee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId ;

    public Employee(Long codeEmployee, Long employeeId, String name, LocalDate birthDate, String address, String phone, double salary, Department department) {
        this.codeEmployee = codeEmployee;
        this.employeeId = employeeId;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    private String name;

    private LocalDate birthDate;

    private String address;

    private String phone ;

    private double salary ;

    public Employee() {

    }



    public Long getCodeEmployee() {
        return codeEmployee;
    }

    public void setCodeEmployee(Long codeEmployee) {
        this.codeEmployee = codeEmployee;
    }



    public void setCode(Long code) {
        this.codeEmployee = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "forienCode", referencedColumnName = "departmentId")
    private Department department;

}
