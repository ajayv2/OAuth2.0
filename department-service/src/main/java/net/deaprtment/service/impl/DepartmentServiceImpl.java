package net.deaprtment.service.impl;

import net.deaprtment.dto.DepartmentDTO;
import net.deaprtment.entity.Department;
import net.deaprtment.exception.DeptCodeAlreadyExistException;
import net.deaprtment.exception.ResourceNotFoundException;
import net.deaprtment.repository.DepartmentRepository;
import net.deaprtment.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

        Department department = modelMapper.map(departmentDTO,Department.class);
        Optional<Department> deptByCode = departmentRepository.findByCode(department.getCode());
        if(deptByCode.isPresent()){
            throw new DeptCodeAlreadyExistException("Department Code already exist", department.getCode());
        }

        Department savedDepartment = departmentRepository.save(department);
        LinkedHashMap<Object,Object> map = new LinkedHashMap<>();
        map.put("AppStatusCode","2001");
        map.put("Description","Department Added Successfully");
        map.put("Department",modelMapper.map(savedDepartment, DepartmentDTO.class));
        System.out.println("Map is "+map);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);

    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department departmentByCode = departmentRepository.findByCode(departmentCode).orElseThrow(
                ()->new ResourceNotFoundException("DEPARTMENT","DEPARTMENT-CODE",departmentCode)
        );
        return modelMapper.map(departmentByCode, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartment(String deptCode,DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findByCode(deptCode).orElseThrow(
                ()->new ResourceNotFoundException("DEPARTMENT","DEPARTMENT_CODE",deptCode)
        );
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        Department savedDept = departmentRepository.save(department);
        return modelMapper.map(savedDept,DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() {
        List<Department> allDepartment = departmentRepository.findAll();
        return allDepartment.stream().map(dept -> modelMapper.map(dept, DepartmentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteDepartment(String departmentCode) {
        Department byDepartmentCode = departmentRepository.findByCode(departmentCode).orElseThrow(
                ()->new ResourceNotFoundException("DEPARTMENT","DEPARTMENT-CODE",departmentCode)
        );
        departmentRepository.delete(byDepartmentCode);
    }
}
