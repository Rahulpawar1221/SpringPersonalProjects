package com.tms.tenant_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.tms.tenant_management_system.dto.LandlordViewProfileDto;
import com.tms.tenant_management_system.dto.TenantLeaseUpdateDto;
import com.tms.tenant_management_system.dto.TenantViewProfileDto;
import com.tms.tenant_management_system.service.LandlordService;

@RestController
@RequestMapping("/api/landlord")
@PreAuthorize("hasRole('LANDLORD')")
public class LandlordController {

    @Autowired
    private LandlordService landlordService;
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ✅ View landlord profile
    @GetMapping("/view/landlord-profile")//tested and working
    public ResponseEntity<LandlordViewProfileDto> viewLandlordProfile() {
        LandlordViewProfileDto dto = landlordService.getLandlordProfile();
        return ResponseEntity.ok(dto);
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ✅ Update landlord profile
    @PutMapping("/update/landlord-profile")//tested and working
    public ResponseEntity<Map<String, String>> updateLandlordProfile(@RequestBody LandlordViewProfileDto dto) {
        return landlordService.updateLandlordProfile(dto);
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ✅ View all tenants under landlord
    @GetMapping("/getalltenants")
    public ResponseEntity<List<TenantViewProfileDto>> getAllTenantsUnderLandlord() {
        List<TenantViewProfileDto> tenants = landlordService.getAllTenantsUnderLandlord();
        return ResponseEntity.ok(tenants);
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ✅ Update lease info of a tenant
    @PutMapping("/update/tenant/lease")
    public ResponseEntity<Map<String, String>> updateTenantLeaseInfo(@RequestBody TenantLeaseUpdateDto dto) {
        return landlordService.updateTenantLeaseInfo(dto);
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ✅ Delete a tenant under landlord
    @DeleteMapping("/delete/tenant/{tenantCode}")
    public ResponseEntity<Map<String, String>> deleteTenant(@PathVariable String tenantCode) {
        return landlordService.deleteTenantByCode(tenantCode);
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
