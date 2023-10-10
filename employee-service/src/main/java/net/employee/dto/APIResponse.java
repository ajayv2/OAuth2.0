package net.employee.dto;

public class APIResponse {
    private EmployeeDTO employee;
    private DepartmentDTO department;
    private OrganizationDto organizationDto;

    public APIResponse(EmployeeDTO employee, DepartmentDTO department, OrganizationDto organizationDto) {
        this.employee = employee;
        this.department = department;
        this.organizationDto = organizationDto;
    }

    public APIResponse() {
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public OrganizationDto getOrganizationDto() {
        return organizationDto;
    }

    public void setOrganizationDto(OrganizationDto organizationDto) {
        this.organizationDto = organizationDto;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "employee=" + employee +
                ", department=" + department +
                ", organizationDto=" + organizationDto +
                '}';
    }
}
