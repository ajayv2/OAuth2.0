package net.orgranization.service;

import net.orgranization.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode);

    OrganizationDto updateOrganization(String organizationCode,OrganizationDto organizationDto);
    List<OrganizationDto> getAllOrganization();

    void deleteOrganization(String organizationCode);
}
