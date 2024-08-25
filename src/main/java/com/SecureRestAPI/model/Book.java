package com.SecureRestAPI.model;

import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@DynamicUpdate
@NoArgsConstructor
@Entity
@Table(name = "tbl_book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;

	@Column(name = "title", nullable = false)
	@NotBlank(message = "Book title must not be empty or null!")
	private String title;

	@Column(name = "author", nullable = false)
	@NotBlank(message = "Author name must not be null")
	private String author;

	@Column(name = "edition", nullable = false)
	@NotBlank(message = "Edition can't be null")
	private String edition;

	private String isbn = UUID.randomUUID().toString();

}
