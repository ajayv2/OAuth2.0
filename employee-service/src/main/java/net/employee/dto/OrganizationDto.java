package net.employee.dto;

import java.time.LocalDateTime;

public class OrganizationDto {

    private Long id;

    private String name;

    private String description;

    private String code;

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
