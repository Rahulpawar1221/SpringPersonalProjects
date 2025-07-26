package com.tms.tenant_management_system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.tms.tenant_management_system.dto.TenantRegistrationDto;
import com.tms.tenant_management_system.dto.TenantUpdateProfileDto;
import com.tms.tenant_management_system.dto.TenantViewProfileDto;
import com.tms.tenant_management_system.service.TenantService;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;
// =====================================================Register a new Tenant under a Landlord==================================
    
    @PostMapping("/register-to-landlord")//TESTED AND WORKING
    @PreAuthorize("hasRole('TENANT')")
    public ResponseEntity<Map<String, String>> registerTenant(@RequestBody TenantRegistrationDto dto,
                                                               @RequestHeader("username") String username) {
        return tenantService.registerTenant(dto, username);
    }
// =====================================================View Tenant's Own Profile================================================
    @GetMapping("/view/profile")
    public ResponseEntity<TenantViewProfileDto> viewTenantProfile(Authentication authentication) {
        String username = authentication.getName();
        TenantViewProfileDto dto = tenantService.viewTenantProfile(username);
        return ResponseEntity.ok(dto);
    }
// =====================================================Update Tenant's Own Profile================================================
    @PutMapping("/update/profile")
    @PreAuthorize("hasRole('TENANT')")//TESTED AND WORKING
    public ResponseEntity<Map<String, String>> updateTenantProfile(@RequestBody TenantUpdateProfileDto dto,
                                                                    Authentication auth) {
        String username = auth.getName();
        return tenantService.updateTenantProfile(username, dto);
    }
// =================================================================================================================================
}






