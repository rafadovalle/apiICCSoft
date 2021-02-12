package com.rafael.inatel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafael.inatel.model.Quotes;

@Repository
public interface QuotesRepository extends CrudRepository<Quotes, Long> {

}
