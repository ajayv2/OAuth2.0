package net.employee.service;

import net.employee.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8888",value = "DEPARTMENT-SERVICE")
@FeignClient(value = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("/api/departments/{department-code}")
    DepartmentDTO getDepartment(@PathVariable("department-code") String departmentCode);

}
