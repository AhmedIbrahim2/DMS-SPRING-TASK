//package com.example.dms_springtask.Controller;
//
//
//import com.example.dms_springtask.Dto.DepartmentDto;
//import com.example.dms_springtask.Model.Department;
//import com.example.dms_springtask.Service.Department_serviceImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("Department")
//public class DepartmentController {
//
//
//    @Autowired
//    Department_serviceImp department_serviceImp;
//
//
//    @GetMapping("/getAllDepartment")
//    public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
//        try {
//            return new ResponseEntity<>(department_serviceImp.getAllDepartment(),HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @GetMapping("/getDepartmentById/{code}")
//    public ResponseEntity<DepartmentDto> getAllDepartment(@PathVariable Long code){
//        try {
//            return new ResponseEntity<>(department_serviceImp.getDepartmentById(code),HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @PostMapping("/addDepartment")
//    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto){
//        try {
//            return new ResponseEntity<>(department_serviceImp.add(departmentDto),HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//    @PutMapping("/editDepartment/{code}")
//    public ResponseEntity<DepartmentDto> editDepartment(@PathVariable Long code , @RequestBody DepartmentDto departmentDto){
//        try {
//            return new ResponseEntity<>(department_serviceImp.edit(code, departmentDto),HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @DeleteMapping("/deleteDepartment/{code}")
//    public ResponseEntity<String> deleteDepartment(@PathVariable Long code){
//        try {
//            department_serviceImp.delete(code);
//            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//}
