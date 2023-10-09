package net.employee.service.impl;

import net.employee.dto.APIResponse;
import net.employee.dto.DepartmentDTO;
import net.employee.dto.EmployeeDTO;
import net.employee.entity.Employee;
import net.employee.repository.EmployeeRepository;
import net.employee.service.APIClient;
import net.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
//    private RestTemplate restTemplate;
//      private WebClient webClient;
    private APIClient apiClient;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, APIClient apiClient) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.apiClient = apiClient;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO mappedDto = modelMapper.map(savedEmployee, EmployeeDTO.class);
        return mappedDto;
    }

    @Override
    public APIResponse getEmployeeById(Long empId) {
        Employee empById = employeeRepository.findById(empId).get();
//        ResponseEntity<DepartmentDTO> departEntity = restTemplate.getForEntity("http://localhost:8888/api/departments/" + empById.getDepartmentCode(),
//                DepartmentDTO.class);
//        DepartmentDTO departmentBody = departEntity.getBody();
//        DepartmentDTO departmentBody =   webClient.get().uri("http://localhost:8888/api/departments/" + empById.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDTO.class)
//                .block();
        DepartmentDTO departmentBody = apiClient.getDepartment(empById.getDepartmentCode());
        EmployeeDTO mappedEmpDto = modelMapper.map(empById, EmployeeDTO.class);
        APIResponse apiResponse = new APIResponse(mappedEmpDto,departmentBody);
        return apiResponse;
    }
}
