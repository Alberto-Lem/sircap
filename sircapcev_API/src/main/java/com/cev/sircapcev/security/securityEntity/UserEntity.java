package com.cev.sircapcev.security.securityEntity;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.cev.sircapcev.security.securityEnums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    List<RoleEnum> roles;
}