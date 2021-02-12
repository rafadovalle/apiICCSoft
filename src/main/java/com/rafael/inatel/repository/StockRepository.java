package com.rafael.inatel.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafael.inatel.model.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{

	Stock findByNome(String nome);
	
}
