package com.cev.sircapcev.security.securityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangePasswordDTO {
    private String oldUsername; // Para identificar al usuario cuyas credenciales se están cambiando.
    private String newUsername; // Nuevo nombre de usuario.
    private String oldPassword; // Contraseña actual.
    private String newPassword; // Nueva contraseña.
    private String confirmPassword; // Confirmación de la nueva contraseña.
}
