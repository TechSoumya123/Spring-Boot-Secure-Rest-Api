package com.SecureRestAPI.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@DynamicUpdate
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", nullable = false)
	@NotBlank(message = "FirstName cannot be null")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotBlank(message = "Lastname cannot be null")
	private String lastName;

	@Column(name = "u_email", unique = true)
	@Email(message = "must be a well formed email-address")
	@NotBlank(message = "Email can't be null")
	private String email;

	@Column(name = "u_password")
	@NotBlank(message = "Password can't be null")
	private String password;

	@Column(name = "user_role")
	private String roles;

}
