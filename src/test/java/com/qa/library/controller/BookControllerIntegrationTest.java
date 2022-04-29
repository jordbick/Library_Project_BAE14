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
import org.junit.platform.engine.TestExecutionResult.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.library.domain.Book;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:testschema.sql",
		"classpath:testdata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	// GET ALL TEST -----------------------------------------------------------
	@Test
	public void getAllTest() throws Exception {
		List<Book> output = new ArrayList<>();
		output.add(new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3));
		String outputAsJSON = mapper.writeValueAsString(output);

		mvc.perform(get("/book/getAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputAsJSON));
	}
	
	// SEARCH TEST ------------------------------------------------------------
	@Test
	public void searchTest() throws Exception {
		List<Book> output = new ArrayList<>();
		output.add(new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3));
		String outputAsJSON = mapper.writeValueAsString(output);
		
		mvc.perform(get("/book/search/Cash").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputAsJSON));

	}

	// GET BY ID TEST ---------------------------------------------------------
	@Test
	public void getByIdTest() throws Exception {
		Book book = new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3);
		String bookAsJSON = mapper.writeValueAsString(book);

		mvc.perform(get("/book/getById/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(bookAsJSON));
	}
	
	// GET BY INVALID ID TEST
	@Test
	public void getByInvalidIdTest() throws Exception {
		MvcResult result = mvc.perform(get("/book/getById/5"))
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
		 
		 String content = result.getResponse().getContentAsString();
		 
		 mvc.perform(get("/book/getById/5"))
		 	.andExpect(status().isNotFound())
		 	.andExpect(content().string(content));
	}
	
	// POST TESTING -----------------------------------------------------------
	@Test
	public void createTest() throws Exception {
		Book input = new Book("Braised Pork", "Any Yu", 2020, "Harvill Secker", 2);
		String inputAsJSON = mapper.writeValueAsString(input);
		
		Book output = new Book(2L, "Braised Pork", "Any Yu", 2020, "Harvill Secker", 2);
		String outputAsJSON = mapper.writeValueAsString(output);
		
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
		
		mvc.perform(put("/book/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(bookAsJSON))
				.andExpect(status().isAccepted())
				.andExpect(content().json(updatedBookAsJSON));
	}
	
	// PUT BY INVLAID ID TESTING
	@Test
	public void putByInvalidIdTest() throws Exception {
		Book book = new Book("Skint Estate", "Cash Carraway", 2019, "Ebury Press", 4);
		String bookAsJSON = mapper.writeValueAsString(book);
		
		MvcResult result = mvc.perform(put("/book/update/5")
				.contentType(MediaType.APPLICATION_JSON)
				.content(bookAsJSON))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		 
		 String content = result.getResponse().getContentAsString();
		 
		 mvc.perform(put("/book/update/5")
		 	.contentType(MediaType.APPLICATION_JSON)
			.content(bookAsJSON))
		 	.andExpect(status().isNotFound())
		 	.andExpect(content().string(content));
	}

	
	// DELETE TESTING ---------------------------------------------------------
	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/book/delete/1"))
				.andExpect(status().isNoContent());
	}
	
	// DELETE BY INVALID ID TESTING --------------------------------------------
	@Test
	public void deleteInvalidId() throws Exception {
		MvcResult result = mvc.perform(delete("/book/delete/5"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
			 
			 boolean content = result.getResponse().getContentAsString().contains("Book with that id does not exists");
			 
			 System.out.println(content);
			 
			 mvc.perform(delete("/book/delete/5"))
			 	.andExpect(status().isNotFound());
	}
}
