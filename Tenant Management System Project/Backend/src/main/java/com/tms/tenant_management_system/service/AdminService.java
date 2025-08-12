package com.tms.tenant_management_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.tenant_management_system.dto.LandlordRegistrationDto;
import com.tms.tenant_management_system.dto.LandlordViewProfileDto;
import com.tms.tenant_management_system.dto.TenantViewProfileDto;
import com.tms.tenant_management_system.entity.Landlord;
import com.tms.tenant_management_system.entity.Role;
import com.tms.tenant_management_system.entity.Tenant;
import com.tms.tenant_management_system.entity.Users;
import com.tms.tenant_management_system.repository.LandlordRepository;
import com.tms.tenant_management_system.repository.TenantRepository;
import com.tms.tenant_management_system.repository.UserRepository;


@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LandlordRepository landlordRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TenantRepository tenantRepository;
	
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@Transactional
	public String registerLandlord(LandlordRegistrationDto landlordRegistrationDto) {
		//If we don't find landlord in the if condition below we create a new landlord.
		if (landlordRepository.existsByAadhaarNumber(landlordRegistrationDto.getLandlordAadhaarNumber())) {
		    return "Landlord with this Aadhaar number already exists.";
		}
		if (landlordRepository.existsByPanCardNumber(landlordRegistrationDto.getLandlordPanCardNumber())) {
		    return "Landlord with this PAN card number already exists.";
		}
        if (userRepository.existsByUsername(landlordRegistrationDto.getUsername())) {
            return "Username is already taken.";
        }

		//First the landlord has to be pushed into the Users Table for his login purpose.
		//hence we create object of Users class and we push the landlord using it into the users table.
		Users user = new Users();
		user.setUsername(landlordRegistrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(landlordRegistrationDto.getPassword()));
		user.setRole(Role.LANDLORD);

		// Save user first
        Users savedUser = userRepository.save(user);
		
		//Generate landlord code
        String landlordCode = UUID.randomUUID().toString();
        
     // Create landlord entity and set fields
        Landlord landlord = new Landlord();
        landlord.setFullName(landlordRegistrationDto.getLandlordFullName());
        landlord.setEmail(landlordRegistrationDto.getLandlordEmail());
        landlord.setPhoneNumber(landlordRegistrationDto.getLandlordPhoneNumber());
        landlord.setPropertyAddress(landlordRegistrationDto.getLandlordPropertyAddress());
        landlord.setAadhaarNumber(landlordRegistrationDto.getLandlordAadhaarNumber());
        landlord.setPanCardNumber(landlordRegistrationDto.getLandlordPanCardNumber());
        landlord.setLandlordCode(landlordCode);
        landlord.setUser(savedUser);
        
        landlordRepository.save(landlord);
        
       
        return "Landlord registered successfully with code: " + landlordCode;
	}
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	public List<TenantViewProfileDto> getAllTenantsForAdmin() {
	    List<Tenant> tenants = tenantRepository.findAll();
	    List<TenantViewProfileDto> dtoList = new ArrayList<>();

	    for (Tenant tenant : tenants) {
	        TenantViewProfileDto dto = new TenantViewProfileDto();
	        dto.setTenantFullName(tenant.getFullName());
	        dto.setTenantCode(tenant.getTenantCode());
	        dtoList.add(dto);
	    }
	    return dtoList;
	}
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	public TenantViewProfileDto getTenantFullProfile(String tenantCode) {
		Tenant tenant = tenantRepository.findByTenantCode(tenantCode)
			    .orElseThrow(() -> new RuntimeException("Tenant not found with code: " + tenantCode));

	      

	    TenantViewProfileDto dto = new TenantViewProfileDto();

	    dto.setTenantFullName(tenant.getFullName());
	    dto.setTenantEmail(tenant.getEmail());
	    dto.setTenantPhoneNumber(tenant.getPhoneNumber());
	    dto.setTenantPermanentAddress(tenant.getPermanentAddress());
	    dto.setAadhaarNumber(tenant.getAadhaarNumber());
	    dto.setPanCardNumber(tenant.getPanCardNumber());
	    dto.setTenantCode(tenant.getTenantCode());
	    dto.setLandlordCode(tenant.getLandlordCode());
	    dto.setLeaseActivationStatus(tenant.isLeaseActivationStatus());

	    return dto;
	}
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	public LandlordViewProfileDto getLandlordByCode(String landlordCode) {
	    Landlord landlord = landlordRepository.findByLandlordCode(landlordCode)
	        .orElseThrow(() -> new RuntimeException("Landlord not found with code: " + landlordCode));

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
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4$$4
	
	public List<LandlordViewProfileDto> getAllLandlords() {
	    List<Landlord> landlords = landlordRepository.findAll();

	    return landlords.stream().map(landlord -> {
	        LandlordViewProfileDto dto = new LandlordViewProfileDto();
	        dto.setLandlordFullName(landlord.getFullName());
	        dto.setLandlordEmail(landlord.getEmail());
	        dto.setLandlordPhoneNumber(landlord.getPhoneNumber());
	        dto.setLandlordPropertyAddress(landlord.getPropertyAddress());
	        dto.setLandlordAadhaarNumber(landlord.getAadhaarNumber());
	        dto.setLandlordPanCardNumber(landlord.getPanCardNumber());
	        dto.setLandlordCode(landlord.getLandlordCode()); // Set to LandlordCode field
	        return dto;
	    }).collect(Collectors.toList());
	}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2
}
