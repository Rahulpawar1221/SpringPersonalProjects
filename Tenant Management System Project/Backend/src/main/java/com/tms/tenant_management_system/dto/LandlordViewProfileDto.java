package com.tms.tenant_management_system.dto;

import java.util.List;

import lombok.Data;

@Data
public class LandlordViewProfileDto {

	private String landlordFullName;
    private String landlordEmail;
    private String landlordPhoneNumber;
    private String landlordPropertyAddress;
    private String landlordAadhaarNumber;
    private String landlordPanCardNumber;
    private String LandlordCode;
}
