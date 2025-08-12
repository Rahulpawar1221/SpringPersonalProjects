package com.tms.tenant_management_system.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class TenantLeaseUpdateDto {//this dto is created for the use of landlord only to update Lease details of tenant.
	
	private double rentAmount;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private boolean leaseActivationStatus;
    
    private String tenantCode;
    private String landlordCode;

}
