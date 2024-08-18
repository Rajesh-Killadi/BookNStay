package com.example.demo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UpdateUser {
	
	@NotNull
	private Integer id;
	@NotBlank(message = "Username is mandatory")
	private String username;
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "Password should be at least 6 characters")
	private String password;
	@Pattern(regexp = "ROLE_USER|ROLE_ADMIN", message = "Role must be either ROLE_USER or ROLE_ADMIN")
	private String role;

}
