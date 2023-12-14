package net.deaprtment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

@Schema(
        description = "DepartmentDTO model information"
)
public class DepartmentDTO {

    private Long id;
    @Schema(
            description = "Department Name"
    )
    @NotEmpty(message = "Department name should not be empty")
    private String name;
    @Schema(
            description = "Department description"
    )
    @NotEmpty(message = "Department name should not be empty")
    private String description;
    @Schema(
            description = "Department Code"
    )
    @NotEmpty(message = "Department name should not be empty")
    private String code;

    public DepartmentDTO(Long id, String name, String description, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public DepartmentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
