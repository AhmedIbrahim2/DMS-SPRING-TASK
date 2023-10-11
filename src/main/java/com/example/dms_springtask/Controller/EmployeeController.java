//package com.example.dms_springtask.Controller;
//
//
//import com.example.dms_springtask.Dto.EmployeeDto;
//import com.example.dms_springtask.Model.Employee;
//import com.example.dms_springtask.Service.Employee_service;
//import com.example.dms_springtask.Service.Employee_serviceImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("Employee")
//@CrossOrigin(origins = "*")
//public class EmployeeController {
//
//
//    @Autowired
//    Employee_serviceImp employee_serviceImp;
//
//
//    @GetMapping("/getAllEmployee")
//    public ResponseEntity<List<EmployeeDto>> getallEmployee(){
//        try {
//            return new ResponseEntity<>(employee_serviceImp.getAllEmployees(), HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @GetMapping("/getEmployeeById/{Code}")
//    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long Code){
//        try {
//            return new ResponseEntity<>(employee_serviceImp.getEmployeeById(Code), HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @PostMapping("/addEmployee")
//    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
//        try {
//            return new ResponseEntity<>(employee_serviceImp.add(employeeDto), HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @PutMapping("/editEmployee/{Code}")
//    public ResponseEntity<EmployeeDto> EditEmployee(@PathVariable Long Code , @RequestBody EmployeeDto e){
//        try {
//            return new ResponseEntity<>(employee_serviceImp.edit(Code,e),HttpStatus.OK);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//
//    @DeleteMapping("deleteEmployee/{Code}")
//    public ResponseEntity<String> DeleteEmployee(@PathVariable Long Code){
//        try {
//            employee_serviceImp.delete(Code);
//            return new ResponseEntity<>("Deleted Successful",HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//
//
//}
