package com.example.demo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Users")
@Data
public class Users {

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
}
