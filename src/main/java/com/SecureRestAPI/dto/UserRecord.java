package com.SecureRestAPI.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRecord(
		@JsonProperty(value = "user_id") Long id,

		@JsonProperty(value = "first_name") String firstName,

		@JsonProperty(value = "last_name") String lastName,

		@JsonProperty(value = "email") String email) implements Serializable {

}
