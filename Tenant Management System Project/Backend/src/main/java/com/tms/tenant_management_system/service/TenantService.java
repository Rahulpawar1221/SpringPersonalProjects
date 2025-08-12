package com.tms.tenant_management_system.service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tms.tenant_management_system.dto.TenantRegistrationDto;
import com.tms.tenant_management_system.dto.TenantUpdateProfileDto;
import com.tms.tenant_management_system.dto.TenantViewProfileDto;
import com.tms.tenant_management_system.entity.Users;
import com.tms.tenant_management_system.entity.Landlord;
import com.tms.tenant_management_system.entity.Tenant;
import com.tms.tenant_management_system.repository.LandlordRepository;
import com.tms.tenant_management_system.repository.TenantRepository;
import com.tms.tenant_management_system.repository.UserRepository;



@Service
public class TenantService {

	@Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private UserRepository userRepository;
    
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    public ResponseEntity<Map<String, String>> registerTenant(TenantRegistrationDto tenantRegistrationDto, String username) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "User not registered. Please register on the website first."));
        }
        Users user = userOpt.get();

        Optional<Landlord> landlordOpt = landlordRepository.findByLandlordCode(tenantRegistrationDto.getLandlordCode());
        if (landlordOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid Landlord Code"));
        }
        Landlord landlord = landlordOpt.get();

        Optional<Tenant> existingTenantOpt = tenantRepository.findByLandlord_LandlordIdAndAadhaarNumberAndPanCardNumber(
                landlord.getLandlordId(),
                tenantRegistrationDto.getAadhaarNumber(),
                tenantRegistrationDto.getPanCardNumber()
        );
        if (existingTenantOpt.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Tenant is already registered under this landlord."));
        }

        Tenant tenant = new Tenant();
        tenant.setFullName(tenantRegistrationDto.getTenantFullName());
        tenant.setEmail(tenantRegistrationDto.getTenantEmail());
        tenant.setPhoneNumber(tenantRegistrationDto.getTenantPhoneNumber());
        tenant.setPermanentAddress(tenantRegistrationDto.getTenantPermanentAddress());
        tenant.setLandlordCode(tenantRegistrationDto.getLandlordCode());
        tenant.setUser(user);
        tenant.setLandlord(landlord);
        tenant.setAadhaarNumber(tenantRegistrationDto.getAadhaarNumber());
        tenant.setPanCardNumber(tenantRegistrationDto.getPanCardNumber());
        tenant.setTenantCode(UUID.randomUUID().toString());

        tenantRepository.save(tenant);

        return ResponseEntity.ok(Map.of("message", "Tenant registered successfully", "tenantCode", tenant.getTenantCode()));
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public TenantViewProfileDto viewTenantProfile(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tenant tenant = tenantRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Tenant profile not found"));

        TenantViewProfileDto dto = new TenantViewProfileDto();
        dto.setTenantFullName(tenant.getFullName());
        dto.setTenantEmail(tenant.getEmail());
        dto.setTenantPhoneNumber(tenant.getPhoneNumber());
        dto.setTenantPermanentAddress(tenant.getPermanentAddress());
        dto.setAadhaarNumber(tenant.getAadhaarNumber());
        dto.setPanCardNumber(tenant.getPanCardNumber());
        dto.setTenantCode(tenant.getTenantCode());
        dto.setLandlordCode(tenant.getLandlordCode());
        dto.setLandlordPhoneNumber(tenant.getLandlord().getPhoneNumber());
        dto.setLeaseActivationStatus(tenant.isLeaseActivationStatus());

        return dto;
    }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ResponseEntity<Map<String, String>> updateTenantProfile(String username, TenantUpdateProfileDto dto) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tenant tenant = tenantRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Tenant profile not found"));

        // Update only personal fields
        tenant.setFullName(dto.getTenantFullName());
        tenant.setEmail(dto.getTenantEmail());
        tenant.setPhoneNumber(dto.getTenantPhoneNumber());
        tenant.setPermanentAddress(dto.getTenantPermanentAddress());
        tenant.setAadhaarNumber(dto.getAadhaarNumber());
        tenant.setPanCardNumber(dto.getPanCardNumber());

        tenantRepository.save(tenant);

        return ResponseEntity.ok(Map.of("message", "Tenant profile updated successfully."));
    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public TenantViewProfileDto getTenantProfileByCode(String tenantCode) {
        Tenant tenant = tenantRepository.findByTenantCode(tenantCode)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        TenantViewProfileDto dto = new TenantViewProfileDto();
        dto.setTenantFullName(tenant.getFullName());
        dto.setTenantEmail(tenant.getEmail());
        dto.setTenantPhoneNumber(tenant.getPhoneNumber());
        dto.setTenantPermanentAddress(tenant.getPermanentAddress());
        dto.setAadhaarNumber(tenant.getAadhaarNumber());
        dto.setPanCardNumber(tenant.getPanCardNumber());
        dto.setTenantCode(tenant.getTenantCode());
        dto.setLandlordCode(tenant.getLandlordCode());
        dto.setLandlordPhoneNumber(tenant.getLandlord().getPhoneNumber());
        dto.setLeaseActivationStatus(tenant.isLeaseActivationStatus());

        return dto;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
