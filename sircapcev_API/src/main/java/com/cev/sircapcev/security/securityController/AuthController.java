package com.cev.sircapcev.security.securityController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cev.sircapcev.security.securityService.UserEntityService;
import com.cev.sircapcev.services.BitacorasService;
import com.cev.sircapcev.global.dto.MessageDto;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.security.securityDTO.ChangePasswordDTO;
import com.cev.sircapcev.security.securityDTO.CreateUserDTO;
import com.cev.sircapcev.security.securityDTO.JwtTokenDTO;
import com.cev.sircapcev.security.securityDTO.LoginUserDTO;
import com.cev.sircapcev.security.securityEntity.UserEntity;
import com.cev.sircapcev.security.securityEnums.RoleEnum;
import com.cev.sircapcev.security.securityRespository.UserEntityRepository;

@Controller
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    BitacorasService bitacorasService;

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    UserEntityRepository userEntityRepository;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<MessageDto> create(@Valid @RequestBody CreateUserDTO dto) throws AttributeException {
        UserEntity userEntity = userEntityService.create(dto);
        return ResponseEntity
                .ok(new MessageDto(HttpStatus.OK, " user " + userEntity.getUsername() + " have been created"));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create-admin")
    public ResponseEntity<MessageDto> createAdmin(@Valid @RequestBody CreateUserDTO dto) throws AttributeException {
        UserEntity userEntity = userEntityService.createAdmin(dto);
        return ResponseEntity
                .ok(new MessageDto(HttpStatus.OK, " admin " + userEntity.getUsername() + " have been created"));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create-user")
    public ResponseEntity<MessageDto> createUser(@Valid @RequestBody CreateUserDTO dto) throws AttributeException {
        UserEntity userEntity = userEntityService.createUser(dto);
        return ResponseEntity
                .ok(new MessageDto(HttpStatus.OK, " user " + userEntity.getUsername() + " have been created"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> create(@Valid @RequestBody LoginUserDTO dto) throws AttributeException {
        JwtTokenDTO JwtTokenDTO = userEntityService.login(dto);
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        bitacorasService.historial(usuario, "Inició sesión");
        return ResponseEntity.ok(JwtTokenDTO);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/change-password")
    public ResponseEntity<MessageDto> changePassword(@Valid @RequestBody ChangePasswordDTO dto)
            throws AttributeException {
        // Obtener el nombre de usuario del contexto de seguridad
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Si el usuario no es administrador y está intentando cambiar las credenciales
        // de otro usuario
        if (!dto.getOldUsername().equals(authenticatedUsername)) {
            UserEntity user = userEntityRepository.findByUsername(authenticatedUsername).orElse(null);

            if (!user.getRoles().contains(RoleEnum.ROLE_ADMIN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new MessageDto(HttpStatus.FORBIDDEN, "Access denied"));
            }
        }

        userEntityService.changePassword(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK,
                "Username and password for " + dto.getNewUsername() + " have been changed"));
    }

}
