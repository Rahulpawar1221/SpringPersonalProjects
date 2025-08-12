package com.tms.tenant_management_system.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tms.tenant_management_system.dto.TenantLeaseUpdateDto;
import com.tms.tenant_management_system.dto.TenantViewProfileDto;
import com.tms.tenant_management_system.dto.LandlordViewProfileDto;
import com.tms.tenant_management_system.entity.Landlord;
import com.tms.tenant_management_system.entity.Tenant;
import com.tms.tenant_management_system.entity.Users;
import com.tms.tenant_management_system.repository.LandlordRepository;
import com.tms.tenant_management_system.repository.TenantRepository;
import com.tms.tenant_management_system.repository.UserRepository;

@Service
public class LandlordService {

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UserRepository userRepository;

    // $$$$$$$$$$$$$$$$$$$$$$$$$$$ Get Current Landlord $$$$$$$$$$$$$$$$$$$$$$$$$$$
    private Landlord getAuthenticatedLandlord() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return landlordRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Landlord not found"));
    }

    // $$$$$$$$$$$$$$$$$$$$$$$$$$$ View My Profile$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ 
    public LandlordViewProfileDto getLandlordProfile() {
        Landlord landlord = getAuthenticatedLandlord();

        LandlordViewProfileDto dto = new LandlordViewProfileDto();
        dto.setLandlordFullName(landlord.getFullName());
        dto.setLandlordEmail(landlord.getEmail());
        dto.setLandlordPhoneNumber(landlord.getPhoneNumber());
        dto.setLandlordPropertyAddress(landlord.getPropertyAddress());
        dto.setLandlordAadhaarNumber(landlord.getAadhaarNumber());
        dto.setLandlordPanCardNumber(landlord.getPanCardNumber());
        dto.setLandlordCode(landlord.getLandlordCode());
        return dto;
    }

    // $$$$$$$$$$$$$$$$$$$$$$$$$$$ Update Lease Info $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44
    
    public ResponseEntity<Map<String, String>> updateTenantLeaseInfo(TenantLeaseUpdateDto dto) {
        Landlord landlord = getAuthenticatedLandlord();

        Tenant tenant = tenantRepository.findByTenantCode(dto.getTenantCode())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        if (!tenant.getLandlord().getLandlordId().equals(landlord.getLandlordId())) {
            throw new RuntimeException("Unauthorized to update this tenant");
        }

        tenant.setRentAmount(dto.getRentAmount());
        tenant.setLeaseStartDate(dto.getLeaseStartDate());
        tenant.setLeaseEndDate(dto.getLeaseEndDate());
        tenant.setLeaseActivationStatus(dto.isLeaseActivationStatus());

        tenantRepository.save(tenant);

        return ResponseEntity.ok(Map.of("message", "Tenant lease info updated successfully"));
    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$ Get All Tenants Under Landlord $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public List<TenantViewProfileDto> getAllTenantsUnderLandlord() {
        Landlord landlord = getAuthenticatedLandlord();

        List<Tenant> tenants = tenantRepository.findByLandlord(landlord);

        return tenants.stream().map(tenant -> {
            TenantViewProfileDto dto = new TenantViewProfileDto();
            dto.setTenantFullName(tenant.getFullName());
            dto.setTenantCode(tenant.getTenantCode());
            dto.setTenantEmail(tenant.getEmail());
            dto.setTenantPhoneNumber(tenant.getPhoneNumber());
            dto.setTenantPermanentAddress(tenant.getPermanentAddress());
            dto.setAadhaarNumber(tenant.getAadhaarNumber());
            dto.setPanCardNumber(tenant.getPanCardNumber());
            dto.setLandlordCode(tenant.getLandlordCode());
            dto.setLandlordPhoneNumber(tenant.getLandlord().getPhoneNumber());
            dto.setLeaseActivationStatus(tenant.isLeaseActivationStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    // $$$$$$$$$$$$$$$$$$$$$$$$$$$ Delete Tenant $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    public ResponseEntity<Map<String, String>> deleteTenantByCode(String tenantCode) {
        Landlord landlord = getAuthenticatedLandlord();

        Tenant tenant = tenantRepository.findByTenantCode(tenantCode)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        if (!tenant.getLandlord().getLandlordId().equals(landlord.getLandlordId())) {
            throw new RuntimeException("Unauthorized to delete this tenant");
        }

        tenantRepository.delete(tenant);

        return ResponseEntity.ok(Map.of("message", "Tenant deleted successfully"));
    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$ Update Landlord Profile $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    public ResponseEntity<Map<String, String>> updateLandlordProfile(LandlordViewProfileDto dto) {
        Landlord landlord = getAuthenticatedLandlord();

        landlord.setFullName(dto.getLandlordFullName());
        landlord.setEmail(dto.getLandlordEmail());
        landlord.setPhoneNumber(dto.getLandlordPhoneNumber());
        landlord.setPropertyAddress(dto.getLandlordPropertyAddress());
        landlord.setAadhaarNumber(dto.getLandlordAadhaarNumber());
        landlord.setPanCardNumber(dto.getLandlordPanCardNumber());

        landlordRepository.save(landlord);

        return ResponseEntity.ok(Map.of("message", "Landlord profile updated successfully"));
    }
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
}
