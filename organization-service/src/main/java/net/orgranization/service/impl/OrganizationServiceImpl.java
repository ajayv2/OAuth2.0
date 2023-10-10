package net.orgranization.service.impl;

import net.orgranization.dto.OrganizationDto;
import net.orgranization.entity.Organization;
import net.orgranization.repository.OrganizationRepository;
import net.orgranization.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;
    private ModelMapper modelMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, ModelMapper modelMapper) {
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization mappedOrg = modelMapper.map(organizationDto, Organization.class);
        Organization savedOrg = organizationRepository.save(mappedOrg);
        return modelMapper.map(savedOrg,OrganizationDto.class);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode).get();
        return modelMapper.map(organization,OrganizationDto.class);
    }
}
