package com.qa.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.library.domain.Book;

public interface BookRepo extends JpaRepository<Book, Long>{

}
