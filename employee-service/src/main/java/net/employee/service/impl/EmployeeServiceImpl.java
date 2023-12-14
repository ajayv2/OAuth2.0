package net.employee.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import net.employee.dto.APIResponse;
import net.employee.dto.DepartmentDTO;
import net.employee.dto.EmployeeDTO;
import net.employee.dto.OrganizationDto;
import net.employee.entity.Employee;
import net.employee.exception.EmailAlreadyExistException;
import net.employee.exception.ResourceNotFoundException;
import net.employee.repository.EmployeeRepository;
import net.employee.service.APIClient;
import net.employee.service.EmployeeService;
import net.employee.service.OrgApiClient;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;

    private APIClient apiClient;
    private OrgApiClient orgApiClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, RestTemplate restTemplate,  APIClient apiClient, OrgApiClient orgApiClient) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;

        this.apiClient = apiClient;
        this.orgApiClient = orgApiClient;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Optional<Employee> empByEmail = employeeRepository.findByEmail(employee.getEmail());
        if(empByEmail.isPresent()){
            throw new EmailAlreadyExistException("Email already exist ", employee.getEmail());
        }
        List<DepartmentDTO> allDepartment = apiClient.getALlDepartment();
        System.out.println(allDepartment);
        if(allDepartment.stream().noneMatch(d -> d.getCode().equals(employee.getDepartmentCode()))){
            throw new ResourceNotFoundException("Department","DepartmentCode",employee.getDepartmentCode());
        }
        List<OrganizationDto> allOrganization = orgApiClient.getAllOrganization();
        System.out.println(allOrganization);
        if(allOrganization.stream().noneMatch(d -> d.getCode().equals(employee.getOrganizationCode()))){
            throw new ResourceNotFoundException("Organization","OrganizationCode",employee.getOrganizationCode());
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

   @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
   // @Retry(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponse getEmployeeById(Long empId) {
        LOGGER.info("inside getEmployeeById() method");
        Employee empById = employeeRepository.findById(empId).orElseThrow(
                ()->new ResourceNotFoundException("Employee","empId",String.valueOf(empId))
        );
        LOGGER.info("Emp is "+empById);
        DepartmentDTO departmentBody = apiClient.getDepartment(empById.getDepartmentCode());
        LOGGER.info("departmentBody is "+departmentBody);

        LOGGER.info("Emp is "+empById.getOrganizationCode());

       OrganizationDto organizationDto = orgApiClient.getOrganizationByCode(empById.getOrganizationCode());

        LOGGER.info("organizationDto is "+organizationDto);
        EmployeeDTO mappedEmpDto = modelMapper.map(empById, EmployeeDTO.class);
        LOGGER.info("mappedEmpDto is "+mappedEmpDto);
        APIResponse apiResponse = new APIResponse(mappedEmpDto,departmentBody,organizationDto);
        LOGGER.info("apiResponse is "+ apiResponse);
        return apiResponse;
    }

    @Override
    public EmployeeDTO updateEmployee(Long empId, EmployeeDTO employeeDTO) {
        LOGGER.info("Inside update employee method");
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                ()->new ResourceNotFoundException("Employee","empId",String.valueOf(empId))
        );
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartmentCode(employeeDTO.getDepartmentCode());
        employee.setOrganizationCode(employeeDTO.getOrganizationCode());
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee,EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll().stream().map(emp->modelMapper.map(emp,EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long empId) {
        LOGGER.info("Inside delete employee method");
        Employee empById = employeeRepository.findById(empId).orElseThrow(
                ()->new ResourceNotFoundException("Employee","empId",String.valueOf(empId))
        );
        employeeRepository.delete(empById);
    }

    public APIResponse getDefaultDepartment(Long empId,Exception exception){
        LOGGER.info("inside getEmployeeById() fallback method");
        Employee empById = employeeRepository.findById(empId).orElseThrow(
                ()->new ResourceNotFoundException("Employee","empId",String.valueOf(empId))
        );
        DepartmentDTO departmentBody = new DepartmentDTO();
        
        OrganizationDto organizationDto = new OrganizationDto();
        
        EmployeeDTO mappedEmpDto = modelMapper.map(empById, EmployeeDTO.class);
        return new APIResponse(mappedEmpDto,departmentBody,organizationDto);
    }

}
