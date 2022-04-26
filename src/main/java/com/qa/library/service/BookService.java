package com.qa.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.library.domain.Book;
import com.qa.library.repo.BookRepo;

@Service
public class BookService {
	
	private BookRepo repo;

	@Autowired
	public BookService(BookRepo repo) {
		super();
		this.repo = repo;
	}
	
	// GET ---------------------------------------------------------
	
	public List<Book> getAll(){
		return repo.findAll();
	}
	
	public Book getById(long id) {
		return repo.findById(id).get();
	}

	// PUT -----------------------------------------------------------
	
	public Book create(Book book) {
		return repo.saveAndFlush(book);
	}
	
	// POST ----------------------------------------------------------
	
	public Book update(long id, Book book) {
		Book bookUpdate = repo.findById(id).get();
		bookUpdate.setAuthor(book.getAuthor());
		bookUpdate.setPublishedYear(book.getPublishedYear());
		bookUpdate.setPublisher(book.getPublisher());
		bookUpdate.setRating(book.getRating());
		bookUpdate.setTitle(book.getTitle());
		return repo.saveAndFlush(bookUpdate);
	}
	
	// DELETE ---------------------------------------------------------
	
	public boolean delete(long id) {
		repo.deleteById(id);
		return !repo.existsById(id);
	}

}
