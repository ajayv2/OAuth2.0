package net.employee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.employee.dto.APIResponse;
import net.employee.dto.EmployeeDTO;
import net.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;

@Tag(
        name="Employee Services to manage employee",
        description = "Employee Service - Create employee,Get employee,Update employee,Delete employee"
)
@RestController
@RequestMapping("api/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Create Employee REST API",
            description = "create employee rest api is used to add employee in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        System.out.println("employee "+employeeDTO);
        EmployeeDTO empDTO = employeeService.saveEmployee(employeeDTO);
        LinkedHashMap<Object,Object> map = new LinkedHashMap<>();
        map.put("AppStatusCode","2001");
        map.put("Description","Employee Added Successfully");
        map.put("Employee",empDTO);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get employee By Id REST API",
            description = "Get employee by id rest api is used to get department by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{emp-id}")
    public ResponseEntity<APIResponse> getEmployee(@PathVariable("emp-id") Long empId){
        APIResponse employeeById = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(employeeById,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PutMapping("/{emp-id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("emp-id") Long empId,@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmpDto = employeeService.updateEmployee(empId, employeeDTO);
        LinkedHashMap<Object,Object> map = new LinkedHashMap<>();
        map.put("AppStatusCode","2002");
        map.put("Description","Employee Updated Successfully");
        map.put("Employee",updatedEmpDto);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @DeleteMapping("/{emp-id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("emp-id") Long empId){
        employeeService.deleteEmployee(empId);
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("AppStatusCode","2000");
        map.put("Description","Employee Deleted Successfully");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
