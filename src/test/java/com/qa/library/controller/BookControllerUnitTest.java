package com.qa.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.library.domain.Book;
import com.qa.library.service.BookService;

@WebMvcTest
public class BookControllerUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private BookService service;
	
	// GET ALL TEST -----------------------------------------------------------
		@Test
		public void getAllTest() throws Exception {
			Book book = new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3);
			List<Book> output = new ArrayList<>();
			output.add(book);
			String outputAsJSON = mapper.writeValueAsString(output);
			
			Mockito.when(service.getAll()).thenReturn(output);

			mvc.perform(get("/book/getAll").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(outputAsJSON));
		}

		// GET BY ID TEST ---------------------------------------------------------
		@Test
		public void getByIdTest() throws Exception {
			Book book = new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3);
			String bookAsJSON = mapper.writeValueAsString(book);
			
			Mockito.when(service.getById(1L)).thenReturn(book);

			mvc.perform(get("/book/getById/1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(bookAsJSON));
		}
		
		// POST TESTING -----------------------------------------------------------
		@Test
		public void createTest() throws Exception {
			Book input = new Book("Braised Pork", "Any Yu", 2020, "Harvill Secker", 2);
			String inputAsJSON = mapper.writeValueAsString(input);
			
			Book output = new Book(2L, "Braised Pork", "Any Yu", 2020, "Harvill Secker", 2);
			String outputAsJSON = mapper.writeValueAsString(output);
			
			Mockito.when(service.create(input)).thenReturn(output);
			
			mvc.perform(post("/book/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(inputAsJSON))
					.andExpect(status().isCreated()) 
					.andExpect(content().json(outputAsJSON));
		}
		
		// PUT TESTING ------------------------------------------------------------ 
		@Test
		public void updateTest() throws Exception {
			Book book = new Book("Skint Estate", "Cash Carraway", 2019, "Ebury Press", 4);
			Book updatedBook = new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 4);
			
			String bookAsJSON = mapper.writeValueAsString(book);
			String updatedBookAsJSON = mapper.writeValueAsString(updatedBook);
			
			Mockito.when(service.update(1L, book)).thenReturn(updatedBook);
			
			mvc.perform(put("/book/update/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(bookAsJSON))
					.andExpect(status().isAccepted())
					.andExpect(content().json(updatedBookAsJSON));
		}
		
		// DELETE TESTING ---------------------------------------------------------
		@Test
		public void deleteTest() throws Exception {
			
			Mockito.when(service.delete(1L)).thenReturn(true);
			
			mvc.perform(delete("/book/delete/1"))
					.andExpect(status().isNoContent());
		}
	
	
}
