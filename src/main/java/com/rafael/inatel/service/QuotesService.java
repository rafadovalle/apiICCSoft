package com.rafael.inatel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.inatel.model.Quotes;
import com.rafael.inatel.repository.QuotesRepository;

@Service
public class QuotesService {

	@Autowired
	private QuotesRepository quotesRepository;

	/*
	 * @Autowired private ModelMapper modelMapper;
	 */

	public Quotes salvar(Quotes quotes) {
		return quotesRepository.save(quotes);
	}
}
