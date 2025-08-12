package com.tms.tenant_management_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.tms.tenant_management_system.dto.LandlordRegistrationDto;
import com.tms.tenant_management_system.dto.LandlordViewProfileDto;
import com.tms.tenant_management_system.dto.TenantViewProfileDto;
import com.tms.tenant_management_system.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
    private AdminService adminService;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register/landlord")//tested and working
	public ResponseEntity<String> registerLandlord(@RequestBody LandlordRegistrationDto landlordRegistrationDto) {
	    String response = adminService.registerLandlord(landlordRegistrationDto);
	    return ResponseEntity.ok(response);
	}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// 👇 View All Tenants (Basic Info Only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view-all-tenants")
    public ResponseEntity<List<TenantViewProfileDto>> getAllTenantsForAdmin() {
        List<TenantViewProfileDto> tenants = adminService.getAllTenantsForAdmin();
        return ResponseEntity.ok(tenants);
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
 // 👇 View Full Profile of a Tenant by Tenant Code
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view/tenant-profile/{tenantCode}")
    public ResponseEntity<TenantViewProfileDto> getTenantFullProfile(@RequestParam String tenantCode) {
        TenantViewProfileDto dto = adminService.getTenantFullProfile(tenantCode);
        return ResponseEntity.ok(dto);
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view-all-landlords")
    public ResponseEntity<List<LandlordViewProfileDto>> getAllLandlords() {
        return ResponseEntity.ok(adminService.getAllLandlords());
    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view/landlord-profile/{landlordCode}")
    public ResponseEntity<LandlordViewProfileDto> getLandlordByCode(@RequestParam String landlordCode) {
        return ResponseEntity.ok(adminService.getLandlordByCode(landlordCode));
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
