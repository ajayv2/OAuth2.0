package net.employee.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import net.employee.dto.APIResponse;
import net.employee.dto.DepartmentDTO;
import net.employee.dto.EmployeeDTO;
import net.employee.dto.OrganizationDto;
import net.employee.entity.Employee;
import net.employee.repository.EmployeeRepository;
import net.employee.service.APIClient;
import net.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
//    private RestTemplate restTemplate;
      private WebClient webClient;
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

   // @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponse getEmployeeById(Long empId) {
        LOGGER.info("inside getEmployeeById() method");
        Employee empById = employeeRepository.findById(empId).get();
//        ResponseEntity<DepartmentDTO> departEntity = restTemplate.getForEntity("http://localhost:8888/api/departments/" + empById.getDepartmentCode(),
//                DepartmentDTO.class);
//        DepartmentDTO departmentBody = departEntity.getBody();
//        DepartmentDTO departmentBody =   webClient.get().uri("http://localhost:8888/api/departments/" + empById.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDTO.class)
//                .block();
        DepartmentDTO departmentBody = apiClient.getDepartment(empById.getDepartmentCode());
        OrganizationDto organizationDto =   webClient.get().uri("http://localhost:8085/api/organization/" + empById.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();
        EmployeeDTO mappedEmpDto = modelMapper.map(empById, EmployeeDTO.class);
        APIResponse apiResponse = new APIResponse(mappedEmpDto,departmentBody,organizationDto);
        return apiResponse;
    }
    public APIResponse getDefaultDepartment(Long empId,Exception exception){
        LOGGER.info("inside getEmployeeById() fallback method");
        Employee empById = employeeRepository.findById(empId).get();
        DepartmentDTO departmentBody = new DepartmentDTO();
        departmentBody.setDepartmentName("R&D Dept");
        departmentBody.setDepartmentCode("RD001");
        departmentBody.setDepartmentDescription("Research and development department");
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("TEST");
        organizationDto.setOrganizationDescription("TEST ORGANIZATION");
        organizationDto.setOrganizationCode("TEST001");
        EmployeeDTO mappedEmpDto = modelMapper.map(empById, EmployeeDTO.class);
        APIResponse apiResponse = new APIResponse(mappedEmpDto,departmentBody,organizationDto);
        return apiResponse;
    }
}
