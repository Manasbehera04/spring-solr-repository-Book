package org.springframework.data.solr.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.PartialUpdate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.example.model.Book;
import org.springframework.data.solr.example.model.Product;
import org.springframework.data.solr.example.model.SearchableProduct;

public class BookRepoImpl implements BookRepo {
	private SolrOperations solrTemplate;

	public BookRepoImpl() {
		super();
	}

	public BookRepoImpl(SolrOperations solrTemplate) {
		super();
		this.solrTemplate = solrTemplate;
	}
	@Override
	public Page<Book> findBooksByCustomImplementation(String value, Pageable page) {
		return solrTemplate.queryForPage("jcg",new SimpleQuery(new SimpleStringCriteria("title:" + value)).setPageRequest(page),
				Book.class);
	}

	@Override
	public void updateBookDescription(String bookId, String desc) {
		PartialUpdate update = new PartialUpdate(SearchableProduct.ID_FIELD, bookId);
		update.setValueOfField("description", desc);

		solrTemplate.saveBean("jcg",update);
		solrTemplate.commit("jcg");

	}

}
