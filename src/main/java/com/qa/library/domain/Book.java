package com.qa.library.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Book {
	
	// Variables ------------------------------------------------------------
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String author;
	
	@Column
	private int publishedYear;
	
	@Column
	private String publisher;
	
	@Column(nullable = false)
	@Min(1)
	@Max(5)
	private int rating;

	// Constructors ----------------------------------------------------------
	
	public Book() {
		super();
	}

	public Book(String title, String author, int publishedYear, String publisher, @Min(1) @Max(5) int rating) {
		super();
		this.title = title;
		this.author = author;
		this.publishedYear = publishedYear;
		this.publisher = publisher;
		this.rating = rating;
	}

	public Book(long id, String title, String author, int publishedYear, String publisher, @Min(1) @Max(5) int rating) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishedYear = publishedYear;
		this.publisher = publisher;
		this.rating = rating;
	}
	
	
	

}
