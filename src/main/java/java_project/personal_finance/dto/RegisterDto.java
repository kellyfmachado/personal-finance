package java_project.personal_finance.dto;

import java_project.personal_finance.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(@NotNull String name, @NotNull String email, @NotNull String password, @NotNull UserRole role ) {

}