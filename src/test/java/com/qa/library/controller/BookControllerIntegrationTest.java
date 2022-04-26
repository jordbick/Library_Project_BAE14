package com.qa.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

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
		Book book = new Book(1L, "Skint Estate", "Cash Carraway", 2019, "Ebury Press", 3);
		List<Book> output = new ArrayList<>();
		output.add(book);
		String outputAsJSON = mapper.writeValueAsString(output);

		mvc.perform(get("/book/getAll").contentType(MediaType.APPLICATION_JSON))
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
}
