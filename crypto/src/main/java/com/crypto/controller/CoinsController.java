package com.crypto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.dto.CoinDTO;
import com.crypto.dto.ReturnsDTO;
import com.crypto.exception.CryptoTrackerException;
import com.crypto.service.CoinService;

@RestController
@RequestMapping(value="/crypto")
public class CoinsController {
	
	@Autowired
	private CoinService coinService;
	
	@GetMapping(value="/coins")
	public ResponseEntity<List<CoinDTO>> getAllCoins() throws CryptoTrackerException{
		List<CoinDTO> coinDTOList = coinService.getAllCoins();
		return new ResponseEntity<>(coinDTOList,HttpStatus.OK);
	}
	
	@GetMapping(value="/{symbol}/{currentPrice}/returns")
	public ResponseEntity<ReturnsDTO> getStatus(@PathVariable double currentPrice, @PathVariable String symbol) throws CryptoTrackerException{
		ReturnsDTO returnsDTO = coinService.getCoinReturns(symbol, currentPrice);
		return new ResponseEntity<>(returnsDTO,HttpStatus.OK);
	}
	
	@PostMapping(value="/addCoin")
	public ResponseEntity<String> addCoin(@Valid @RequestBody CoinDTO coin) throws CryptoTrackerException{
		coinService.addCoin(coin);		
		return new ResponseEntity<>("Coin Added.",HttpStatus.CREATED);
	}
	
	@PutMapping(value="/updateInvestment")
	public ResponseEntity<String> updateInvestment(@Valid @RequestBody CoinDTO coinDTO) throws CryptoTrackerException{
		coinService.updateCoin(coinDTO);
		return new ResponseEntity<>("Coin Updated.",HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteCoin/{symbol}")
	public ResponseEntity<String> deleteCoin(@PathVariable String symbol) throws CryptoTrackerException{
		coinService.deleteCoin(symbol);
		return new ResponseEntity<>(symbol+" deleted",HttpStatus.OK);
	}
	

}
