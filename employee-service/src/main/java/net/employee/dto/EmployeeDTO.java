package net.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Schema(
        description = "EmployeeDTO model information"
)
public class EmployeeDTO {
    private Long id;
    @Schema(
            description = "Employee First Name"
    )
    @NotEmpty(message = "First name should not be blank")
    private String firstName;
    @Schema(
            description = "Employee Last Name"
    )
    @NotEmpty(message = "Last name should not be blank")
    private String lastName;
    @Schema(
            description = "Employee Email"
    )
    @NotEmpty(message = "email should not be blank")
    @Email(message = "Email should be in valid format")
    private String email;
    @Schema(
            description = "Employee Department Code"
    )
    @NotEmpty(message = "department code should not be blank")
    private String departmentCode;
    @Schema(
            description = "Employee Organization Code"
    )
    @NotEmpty(message = "organization code should not be blank")
    private String organizationCode;

    public EmployeeDTO(Long id, String firstName, String lastName, String email, String departmentCode, String organizationCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentCode = departmentCode;
        this.organizationCode = organizationCode;
    }

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                '}';
    }
}
