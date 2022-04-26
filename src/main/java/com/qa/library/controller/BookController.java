package com.qa.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.library.domain.Book;
import com.qa.library.service.BookService;

@RestController
@CrossOrigin
@RequestMapping(path = "/book")
public class BookController {
	
	private BookService service;

	@Autowired
	public BookController(BookService service) {
		super();
		this.service = service;
	}
	
	// GET -------------------------------------------------
	@GetMapping("/getAll")
	public ResponseEntity<List<Book>> getAll(){
		return new ResponseEntity<List<Book>>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Book> getById(@PathVariable long id){
		return new ResponseEntity<Book>(service.getById(id), HttpStatus.OK);
	}
	
	// POST --------------------------------------------------
	@PostMapping("/create")
	public ResponseEntity<Book> create(@Valid @RequestBody Book book){
		return new ResponseEntity<Book>(service.create(book), HttpStatus.CREATED);
	}
	
	// PUT --------------------------------------------------
	@PutMapping("/update/{id}")
	public ResponseEntity<Book> update(@PathVariable long id, @Valid @RequestBody Book book){
		return new ResponseEntity<Book>(service.update(id, book), HttpStatus.ACCEPTED);
	}
	

}
