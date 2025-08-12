package com.tms.tenant_management_system.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Tenant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenantId;
	
	// ========== Unique Tenant Code ==========
    @Column(nullable = false, unique = true)
    private String tenantCode;

    // ========== Personal Information ==========
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String permanentAddress;
    
    @Column(nullable = false, unique = true, length = 12)
    private String aadhaarNumber;

    @Column(nullable = false, unique = true, length = 10)
    private String panCardNumber;

    // ========== Relationship to User (Login Credentials) ==========
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private Users user;

    // ========== Relationship to Landlord ==========
    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private Landlord landlord;

    // ========== Read-Only Fields (controlled by Landlord) ==========
    private Double rentAmount;

    private LocalDate leaseStartDate;

    private LocalDate leaseEndDate;

    private boolean leaseActivationStatus;  // Indicates if lease is currently active

    // ========== Optional Metadata ==========
    @Column(nullable = false)
    private String landlordCode;  // Used by tenant during registration to link to correct landlord
}
