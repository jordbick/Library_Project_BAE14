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
	

}
