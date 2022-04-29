package com.qa.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.library.domain.Book;
import com.qa.library.exception.BookNotFoundException;
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
	
	public Book getById(long id) throws BookNotFoundException {
		return repo.findById(id).orElseThrow(BookNotFoundException::new);
	}
	
	// SEARCH

	// POST -----------------------------------------------------------
	
	public Book create(Book book) {
		return repo.saveAndFlush(book);
	}
	
	// PUT ----------------------------------------------------------
	
	public Book update(long id, Book book) throws BookNotFoundException {
		Book bookUpdate = repo.findById(id).orElseThrow(BookNotFoundException::new);
		bookUpdate.setAuthor(book.getAuthor());
		bookUpdate.setPublishedYear(book.getPublishedYear());
		bookUpdate.setPublisher(book.getPublisher());
		bookUpdate.setRating(book.getRating());
		bookUpdate.setTitle(book.getTitle());
		return repo.saveAndFlush(bookUpdate);
	}
	
	// DELETE ---------------------------------------------------------
	
	public boolean delete(long id) throws BookNotFoundException {
		if (!repo.existsById(id)) {
			throw new BookNotFoundException();
		} else {
			repo.deleteById(id);
			return !repo.existsById(id);
		}
	}

}
