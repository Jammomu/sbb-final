package com.mysite.sbbfinal.user.dto;

import java.util.List;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private List<Role> roles;
}
