package com.tms.tenant_management_system.dto;

import lombok.Data;



//This Dto registeres the Tenant to Landlord using landlordcode.
@Data
public class TenantRegistrationDto {

	private String tenantFullName;
	
	private String username;

    private String tenantEmail;

    private String tenantPhoneNumber;

    private String tenantPermanentAddress;
    private String landlordCode; // used to identify the landlord during registration

    private String aadhaarNumber;

    private String panCardNumber;
}
