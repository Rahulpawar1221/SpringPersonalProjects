package com.tms.tenant_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.tenant_management_system.entity.Landlord;
import com.tms.tenant_management_system.entity.Users;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Long>{

	boolean existsByAadhaarNumber(String aadhaarNumber);

	boolean existsByPanCardNumber(String panCardNumber);
	
	Optional<Landlord> findByUser(Users user);

    Optional<Landlord> findByLandlordCode(String landlordCode);
}
