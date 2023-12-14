package net.deaprtment.service;

import net.deaprtment.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
     DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
     DepartmentDTO getDepartmentByCode(String departmentCOde);

     DepartmentDTO updateDepartment(String deptCode,DepartmentDTO departmentDTO);
     List<DepartmentDTO> getAllDepartment();

     void deleteDepartment(String departmentCode);

}
