package com.bonappetit.model.dtos;

import com.bonappetit.vallidation.FieldMatch;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class RegistrationDto {
    private Long id;
    @Size(min = 3, max = 20, message = "Username length be between 3 and 20 characters")
    private String username;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    @NotNull(message = "Email cannot be empty")
    private String email;
//    @NotEmpty(message = "Password cannot be empty")
//        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
//        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", message = "Password must contain at least one digit, one lowercase and one uppercase letter")
@Size(min = 3, max = 20, message = "Password length be between 3 and 20 characters")

private String password;
    @NotEmpty(message = "Confirm password cannot be empty")
    private String confirmPassword;
}
