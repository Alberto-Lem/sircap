package com.cev.sircapcev.security.securityService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.entity.ConfiguracionEntity;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.repositories.ConfiguracionRepository;
import com.cev.sircapcev.security.jwt.JwtProvider;
import com.cev.sircapcev.security.securityDTO.ChangePasswordDTO;
import com.cev.sircapcev.security.securityDTO.CreateUserDTO;
import com.cev.sircapcev.security.securityDTO.JwtTokenDTO;
import com.cev.sircapcev.security.securityDTO.LoginUserDTO;
import com.cev.sircapcev.security.securityEntity.UserEntity;
import com.cev.sircapcev.security.securityEnums.RoleEnum;
import com.cev.sircapcev.security.securityRespository.UserEntityRepository;
import com.cev.sircapcev.services.AuditLogService;

@Service
public class UserEntityService {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ConfiguracionRepository configuracionRepository;

    @Autowired
    private AuditLogService auditLogService;

    public UserEntity create(CreateUserDTO dto) throws AttributeException {
        if (userEntityRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("Usuario ya existe en este sistema");
        if (userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("Este correo ya esta en uso");
        if (dto.getRoles().isEmpty())
            throw new AttributeException("Roles are mandatory");
        return userEntityRepository.save(mapUserFromDto(dto));
    }

    public UserEntity createAdmin(CreateUserDTO dto) throws AttributeException {
        if (userEntityRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("Usuario ya existe en este sistema");
        if (userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("Este correo ya esta en uso");
        List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        dto.setRoles(roles);
        return userEntityRepository.save(mapUserFromDto(dto));
    }

    public UserEntity createUser(CreateUserDTO dto) throws AttributeException {
        if (userEntityRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("Usuario ya existe en este sistema");
        if (userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("Este correo ya esta en uso");
        List<String> roles = Arrays.asList("ROLE_USER");
        dto.setRoles(roles);
        return userEntityRepository.save(mapUserFromDto(dto));
    }

    public JwtTokenDTO login(LoginUserDTO dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtTokenDTO(token);

    }

    private UserEntity mapUserFromDto(CreateUserDTO dto) {
        String password = passwordEncoder.encode(dto.getPassword());
        List<RoleEnum> roles = dto.getRoles().stream().map(rol -> RoleEnum.valueOf(rol)).collect(Collectors.toList());
        return new UserEntity(null, dto.getUsername(), dto.getEmail(), password, roles);
    }

    @PostConstruct
    public void init() throws AttributeException {
        // Verificar si ya existe una configuración inicial
        if (!configuracionRepository.existsByConfigKey("Initialized")) {
            CreateUserDTO defaultAdminDTO = new CreateUserDTO();
            defaultAdminDTO.setUsername("Admin");
            defaultAdminDTO.setPassword("Admin");
            defaultAdminDTO.setEmail("admin@admin.com");
            createAdmin(defaultAdminDTO);

            // Guardar la configuración inicial en la base de datos
            ConfiguracionEntity config = new ConfiguracionEntity();
            config.setConfigKey("Initialized");
            config.setConfigValue(true);
            config.setInitializationDate(new Date());
            configuracionRepository.save(config);
            auditLogService.logEvent("El sistema se ha inicializado por primera vez.");
        } else {
            auditLogService.logEvent("El sistema ha sido reiniciado.");
        }
    }

    public void changePassword(ChangePasswordDTO dto) throws AttributeException {
        UserEntity user = userEntityRepository.findByUsername(dto.getOldUsername())
                .orElseThrow(() -> new AttributeException("User not found"));

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new AttributeException("Old password is incorrect");
        }

        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new AttributeException("La nueva contraseña no coincide con la confirmación");
        }

        user.setUsername(dto.getNewUsername());
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userEntityRepository.save(user);
    }
}
