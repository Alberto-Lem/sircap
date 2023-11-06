package com.cev.sircapcev.security.securityDTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserDTO {
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    //@NotEmpty(message = "Roles is mandatory")
    List<String> roles = new ArrayList<>();
}
