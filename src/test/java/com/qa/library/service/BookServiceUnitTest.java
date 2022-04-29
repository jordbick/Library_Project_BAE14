package com.qa.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.qa.library.domain.Book;
import com.qa.library.repo.BookRepo;

@SpringBootTest
public class BookServiceUnitTest {

	@Autowired
	private BookService service;

	@MockBean
	private BookRepo repo;

	// GET ALL TEST ----------------------------------------------------
	@Test
	public void getAllTest() {
		List<Book> output = new ArrayList<>();
		output.add(new Book("Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3));

		when(repo.findAll()).thenReturn(output);

		assertEquals(service.getAll(), output);

		verify(repo, times(1)).findAll();
	}

	// SEARCH TEST
	@Test
	public void searchTest() {
		List<Book> output = new ArrayList<>();
		output.add(new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3));


		when(repo.searchSQL("Cash")).thenReturn(output);

		assertEquals(service.search("Cash"), output);

		verify(repo, times(1)).searchSQL("Cash");

	}

	// GET BY ID TEST ---------------------------------------------------
	@Test
	public void getByIdTest() {
		Optional<Book> bookOptional = Optional
				.of(new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3));
		Book book = new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3);

		when(repo.findById(1L)).thenReturn(bookOptional);

		assertEquals(book, service.getById(1L));

		verify(repo, times(1)).findById(1L);
	}

	// POST TEST ---------------------------------------------------------
	@Test
	public void createTest() {
		Book input = new Book("Braised Pork", "Any Yu", 2020, "Harvill Secker", 2);
		Book output = new Book(2L, "Braised Pork", "Any Yu", 2020, "Harvill Secker", 2);

		when(repo.saveAndFlush(input)).thenReturn(output);

		assertEquals(output, service.create(input));

		verify(repo, times(1)).saveAndFlush(input);
	}

	// PUT TEST ------------------------------------------------------------
	@Test
	public void updateTest() {
		Optional<Book> book = Optional.of(new Book("Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3));
		Book toUpdate = new Book("Skint Estate", "Cash Carraway", 2019, "Ebury Press", 4);
		Book updatedBook = new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 4);

		when(repo.findById(1L)).thenReturn(book);
		when(repo.saveAndFlush(toUpdate)).thenReturn(updatedBook);

		assertEquals(updatedBook, service.update(1L, toUpdate));

		verify(repo, times(1)).findById(1L);
		verify(repo, times(1)).saveAndFlush(toUpdate);
	}

	// DELETE TEST ----------------------------------------------------------
	@Test
	public void deleteTest() {
		when(repo.existsById(1L)).thenReturn(true);

		service.delete(1L);
		assertEquals(Optional.empty(), repo.findById(1L));

		verify(repo, times(2)).existsById(1L);
		verify(repo, times(1)).deleteById(1L);
	}

}
