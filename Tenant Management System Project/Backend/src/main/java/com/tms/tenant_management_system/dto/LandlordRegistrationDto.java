package com.tms.tenant_management_system.dto;

import lombok.Data;

@Data
public class LandlordRegistrationDto {

	private String landlordFullName;
    private String landlordEmail;
    private String landlordPhoneNumber;
    private String landlordPropertyAddress;
    private String landlordAadhaarNumber;
    private String landlordPanCardNumber;
   // private String LandlordCode; // not passing it here because it's auto generated during landlord registration.
    private String username;     // For Users table
    private String password;     // For Users table
}
