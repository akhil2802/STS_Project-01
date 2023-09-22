package com.emailverification.registration;

import jakarta.validation.constraints.NotEmpty;

public record RegistrationRequest(
        String firstName,
        String lastName,
        @NotEmpty String email,
        String password,
        String role) {
}