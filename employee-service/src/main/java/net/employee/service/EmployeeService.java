package net.employee.service;

import net.employee.dto.APIResponse;
import net.employee.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    APIResponse getEmployeeById(Long empId);

}
