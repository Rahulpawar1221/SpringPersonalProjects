package com.tms.tenant_management_system.entity;

import java.util.List;
import com.tms.tenant_management_system.entity.Tenant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Landlord {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landlordId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String propertyAddress;

    @Column(nullable = false, unique = true)
    private String landlordCode;

    @Column(nullable = false, unique = true, length = 12)
    private String aadhaarNumber;

    @Column(nullable = false, unique = true, length = 10)
    private String panCardNumber;

    // Relationship with Users table
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private Users user;

    // One-to-Many relationship with tenants
    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Tenant> tenants;
}
