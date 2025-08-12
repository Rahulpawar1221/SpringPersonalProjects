package com.tms.tenant_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.tenant_management_system.entity.Landlord;
import com.tms.tenant_management_system.entity.Tenant;
import com.tms.tenant_management_system.entity.Users;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	Optional<Tenant> findByAadhaarNumberAndPanCardNumber(String aadhaarNumber, String panCardNumber);

	// Check if tenant exists by user reference
    boolean existsByUser(Users user);
    
    Optional<Tenant> findByUser(Users user);
    
    Optional<Tenant> findByTenantCode(String tenantCode);

    Optional<Tenant> findByLandlord_LandlordIdAndAadhaarNumberAndPanCardNumber(
    	    Long landlordId, String aadhaarNumber, String panCardNumber);
    
    List<Tenant> findByLandlord(Landlord landlord);


}
