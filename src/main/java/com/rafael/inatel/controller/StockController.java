package com.rafael.inatel.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.inatel.model.Stock;
import com.rafael.inatel.repository.StockRepository;
import com.rafael.inatel.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired // Dizendo que quero uma instância da Interface nesse ponto.
	private StockRepository stockRepository;
	
	@Autowired
	private StockService stockService;
	
	@GetMapping
	public List<Stock> listar() {
		return stockService.listar();
	}
	
	@GetMapping("/{stockName}")
	public ResponseEntity<Stock> buscar(@PathVariable String stockName, ModelMap map) {
		Optional<Stock> stock = Optional.ofNullable(verificaStock(stockName));
		if (stock.isPresent()) {
			return ResponseEntity.ok(stock.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Stock adicionar(@Valid @RequestBody Stock stock) {		
		return stockService.salvar(stock);
	}
	
	/**************************************/
	
	@PatchMapping("/{stockName}")
	public ResponseEntity<Stock> atualizar (@Valid @PathVariable String stockName, 
			@RequestBody Stock stock){
		
		if(stockRepository.findByNome(stockName) == null) {
			return ResponseEntity.notFound().build();
		}
		
		stock.setNome(stockName);
		stock = stockService.salvar(stock);
		
		return ResponseEntity.ok(stock);
	}
	
	/**************************************/
	@DeleteMapping("/{stockName}")
	public ResponseEntity<Void> excluir (@PathVariable String stockName){
		Optional<Stock> stock = Optional.ofNullable(verificaStock(stockName));
		if(!stock.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		stockService.excluir(stock.get().getId());
		
		return ResponseEntity.noContent().build();	
	}

	private Stock verificaStock(String stockName) {
		Stock stock = stockRepository.findByNome(stockName);
		return stock;
	}
	
}
