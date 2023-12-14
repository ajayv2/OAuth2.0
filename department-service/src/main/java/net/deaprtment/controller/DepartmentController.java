package net.deaprtment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.deaprtment.dto.DepartmentDTO;
import net.deaprtment.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;

@Tag(
        name="Department Services to manage department",
        description = "Department Service - Create department,Get Department,Update Department,Delete Department"
)
@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(
            summary = "Create department REST API",
            description = "create department rest api is used to add department in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO savedDepartment = departmentService.saveDepartment(departmentDTO);
        System.out.println("Saved department is "+savedDepartment);
        LinkedHashMap<Object,Object> map = new LinkedHashMap<>();
        map.put("AppStatusCode","2001");
        map.put("Description","Department Added Successfully");
        map.put("Department",savedDepartment);
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @Operation(
            summary = "Get department By Id REST API",
            description = "Get department by id rest api is used to get department by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{department-code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDTO departmentByCode = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentByCode,HttpStatus.OK);
    }


    @Operation(
            summary = "Get All department  REST API",
            description = "Get all department  rest api is used to get all department  from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getALlDepartment(){
        List<DepartmentDTO> allDepartment = departmentService.getAllDepartment();
        return ResponseEntity.ok(allDepartment);
    }

    @Operation(
            summary = "Update department REST API",
            description = "Update department rest api is used to update the department"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("/{department-code}")
    public ResponseEntity<?> updateDepartment(@PathVariable("department-code") String departmentCode,
                                                          @RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentCode, departmentDTO);
        LinkedHashMap<Object,Object> map = new LinkedHashMap<>();
        map.put("AppStatusCode","2002");
        map.put("Description","Department Updated Successfully");
        map.put("Department",updatedDepartment);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete department By Code REST API",
            description = "delete department by code rest api is used to delete department by code from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("/{department-code}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("department-code") String departmentCode){
        departmentService.deleteDepartment(departmentCode);
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("AppStatusCode","2000");
        map.put("Description","Department Deleted Successfully");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
