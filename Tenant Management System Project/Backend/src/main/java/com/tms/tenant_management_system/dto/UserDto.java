package com.tms.tenant_management_system.dto;

import com.tms.tenant_management_system.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
  //  private Long userId;
    private String username;
    private String password;  // Required for registration/login
    //private Role role;        // Can be used for setting/viewing role
}
