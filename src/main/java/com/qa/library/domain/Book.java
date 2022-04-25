package com.qa.library.domain;

import java.util.Objects;

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

	// Getters and Setters ----------------------------------------------------
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	
	// toString -------------------------------------------------------------
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publishedYear=" + publishedYear
				+ ", publisher=" + publisher + ", rating=" + rating + "]";
	}

	// hashCode and equals ---------------------------------------------------
	
	@Override
	public int hashCode() {
		return Objects.hash(author, id, publishedYear, publisher, rating, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && id == other.id && publishedYear == other.publishedYear
				&& Objects.equals(publisher, other.publisher) && rating == other.rating
				&& Objects.equals(title, other.title);
	}
	
	
	
}
