package com.qa.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.library.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>{
	
	@Query(value = "SELECT * FROM book WHERE author LIKE %?1% OR title LIKE %?1% "
			+ "OR publisher LIKE %?1%", 
			nativeQuery = true) 
	public List<Book> searchSQL(String param);

}
