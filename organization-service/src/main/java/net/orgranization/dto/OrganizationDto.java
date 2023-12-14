package net.orgranization.dto;




import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Schema(
        description = "OrganizationDTO model information"
)
public class OrganizationDto {

    private Long id;

    @Schema(
            description = "Organization Name"
    )
    @NotEmpty(message = "organization name should not be blank")
    private String name;
    @Schema(
            description = "Organization Description"
    )
    @NotEmpty(message = "organization description should not be blank")
    private String description;
    @Schema(
            description = "Organization Code"
    )
    @NotEmpty(message = "organization code should not be blank")
    private String code;


    @Schema(
            description = "Organization created date"
    )
    private LocalDateTime createdDate;

    public OrganizationDto(Long id, String name, String description, String code, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.createdDate = createdDate;
    }

    public OrganizationDto() {
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "OrganizationDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
