package net.orgranization.controller;

import net.orgranization.dto.OrganizationDto;
import net.orgranization.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String organizationCode){
        OrganizationDto organizationByCode = organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(organizationByCode,HttpStatus.OK);
    }
}
