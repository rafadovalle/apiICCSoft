package com.rafael.inatel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.inatel.exception.VerificacaoException;
import com.rafael.inatel.model.Stock;
import com.rafael.inatel.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	/*
	 * @Autowired private QuotesService quotesService;
	 */
	
	public Stock salvar(Stock stock) {
		Stock stockExiste = stockRepository.findByNome(stock.getNome());
		
		if(stockExiste != null && !stockExiste.equals(stock)) {
			throw new VerificacaoException("Já há uma ação com este nome no banco");
		}
	
		return stockRepository.save(stock);
	}
	
	public void excluir (Long id) {
		stockRepository.deleteById(id);
	}
	
	public List<Stock> listar () {
		return (List<Stock>) stockRepository.findAll();
	}
}
