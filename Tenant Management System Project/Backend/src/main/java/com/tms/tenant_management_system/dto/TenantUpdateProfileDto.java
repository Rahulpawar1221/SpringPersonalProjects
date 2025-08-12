package com.tms.tenant_management_system.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class TenantUpdateProfileDto {

	private String tenantFullName;
    private String tenantEmail;
    private String tenantPhoneNumber;
    private String tenantPermanentAddress;
    private String aadhaarNumber;
    private String panCardNumber;    
}
