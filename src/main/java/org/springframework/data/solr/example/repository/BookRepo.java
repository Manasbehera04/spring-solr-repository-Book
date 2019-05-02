package org.springframework.data.solr.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.example.model.Book;
import org.springframework.data.solr.example.model.Product;
import org.springframework.data.solr.repository.Query;



public interface BookRepo
{

	Page<Book> findBooksByCustomImplementation(String value, Pageable page);

	void updateBookDescription(String productId, String desc);
		
}
