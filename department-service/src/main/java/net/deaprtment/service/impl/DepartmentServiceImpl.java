package net.deaprtment.service.impl;

import net.deaprtment.dto.DepartmentDTO;
import net.deaprtment.entity.Department;
import net.deaprtment.repository.DepartmentRepository;
import net.deaprtment.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDTO mappedDepartment = modelMapper.map(savedDepartment, DepartmentDTO.class);

        return mappedDepartment;
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department departmentByCode = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDTO departmentDTO = modelMapper.map(departmentByCode, DepartmentDTO.class);
        return departmentDTO;
    }
}
