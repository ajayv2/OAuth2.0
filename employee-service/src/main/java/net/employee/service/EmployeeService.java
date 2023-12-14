package net.employee.service;

import net.employee.dto.APIResponse;
import net.employee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    APIResponse getEmployeeById(Long empId);
    EmployeeDTO updateEmployee(Long empId,EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee();

    void deleteEmployee(Long empId);

}
