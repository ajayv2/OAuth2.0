package net.deaprtment.service;

import net.deaprtment.dto.DepartmentDTO;

public interface DepartmentService {
     DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
     DepartmentDTO getDepartmentByCode(String departmentCOde);

}
