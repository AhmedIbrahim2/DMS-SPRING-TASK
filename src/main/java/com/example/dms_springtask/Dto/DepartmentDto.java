package com.example.dms_springtask.Dto;

import com.example.dms_springtask.Model.Department;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public  class DepartmentDto {
    private Long codeDepartment ;

    private Long departmentId ;

    private String name ;

    private String description;

  public static DepartmentDto departmentToDto(Department department){
       DepartmentDto departmentDto = new DepartmentDto();
       departmentDto.setDepartmentId(department.getDepartmentId());
       departmentDto.setCodeDepartment(department.getCode());
       departmentDto.setName(department.getName());
       departmentDto.setDescription(department.getDescription());

       return departmentDto;
    }

    public static Department dtoToDepartment(DepartmentDto departmentDto){
        return Department.builder()
                .departmentId(departmentDto.getDepartmentId())
                .codeDepartment(departmentDto.getCodeDepartment())
                .name(departmentDto.getName())
                .Description(departmentDto.getDescription())
                .build();
    }



}