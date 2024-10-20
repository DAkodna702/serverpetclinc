package com.serverpet.server.DTO;

import com.serverpet.server.Util.Roles;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
                                    @NotBlank String password,
                                    @NotBlank Roles role,
                                    @NotBlank String email,
                                    @NotBlank Integer phone)
                                   {
}