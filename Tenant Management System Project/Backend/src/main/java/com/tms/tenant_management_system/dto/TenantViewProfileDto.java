package com.tms.tenant_management_system.dto;

import lombok.Data;

@Data
public class TenantViewProfileDto {

	private String tenantFullName;
    private String tenantEmail;
    private String tenantPhoneNumber;
    private String tenantPermanentAddress;
    private String aadhaarNumber;
    private String panCardNumber;
    
    
    private String tenantCode;
    private String landlordCode;
    private String landlordPhoneNumber;
    
    private boolean leaseActivationStatus;
}
