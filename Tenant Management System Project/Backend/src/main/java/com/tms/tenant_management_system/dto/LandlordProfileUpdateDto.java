package com.tms.tenant_management_system.dto;

import lombok.Data;

@Data
public class LandlordProfileUpdateDto {

	private String landlordFullName;
    private String landlordEmail;
    private String landlordPhoneNumber;
    private String landlordPropertyAddress;
    private String landlordAadhaarNumber;
    private String landlordPanCardNumber;
}
