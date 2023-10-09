package net.employee.controller;

import net.employee.dto.APIResponse;
import net.employee.dto.EmployeeDTO;
import net.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO empDTO = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(empDTO, HttpStatus.CREATED);
    }

    @GetMapping("{emp-id}")
    public ResponseEntity<APIResponse> getEmployee(@PathVariable("emp-id") Long empId){
        APIResponse employeeById = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(employeeById,HttpStatus.OK);
    }

}
