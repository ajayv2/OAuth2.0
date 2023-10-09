package net.employee.dto;

public class APIResponse {
    private EmployeeDTO employee;
    private DepartmentDTO department;

    public APIResponse(EmployeeDTO employee, DepartmentDTO department) {
        this.employee = employee;
        this.department = department;
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

    @Override
    public String toString() {
        return "APIResponse{" +
                "employee=" + employee +
                ", department=" + department +
                '}';
    }
}
