package net.employee.service;

import net.employee.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "ORGANIZATION-SERVICE")
public interface OrgApiClient {
    @GetMapping("/api/organizations")
    public List<OrganizationDto> getAllOrganization();

    @GetMapping("/api/organizations/{organizationCode}")
    public OrganizationDto getOrganizationByCode(@PathVariable String organizationCode);

}
