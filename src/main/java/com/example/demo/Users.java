package com.example.demo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table("Users")
@Data
public class Users implements UserDetails {

    @Id
    private Integer id;

    @Column("firstname")
    private String firstName;

    @Column("lastname")
    private String lastName;

    private String password;
    private String email;

    private Integer age;

    @Column("clientCode")
    private String clientCode;

    private String role;

    @Column("isAccountLocked")
    private Boolean isAccountLocked;
    @Column("isAccountExpired")
    private Boolean isAccountExpired;
    @Column("isPasswordExpired")
    private Boolean isPasswordExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> this.role);
    }

    @Override
    public String getUsername() {
        return this.firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isPasswordExpired;
    }

    @Override
    public boolean isEnabled() {
        return !this.isAccountLocked && !this.isAccountExpired && !this.isPasswordExpired;
    }

    public String getPassword() {
        return "{noop}"+password;
    }
}
