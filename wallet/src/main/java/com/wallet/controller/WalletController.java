package com.wallet.controller;

import java.util.List;

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

import com.wallet.service.WalletService;
import com.wallet.dto.CoinDTO;
import com.wallet.dto.WalletDTO;
import com.wallet.exception.CryptoTrackerException;

@RestController
@RequestMapping(value="/wallet")
public class WalletController {

	@Autowired
	WalletService walletService;
	
	@GetMapping(value="/{walletId}/coins")
	public ResponseEntity<List<CoinDTO>> getWalletCoins(@PathVariable Integer walletId) throws CryptoTrackerException{
		List<CoinDTO> coinDTOList = walletService.getWalletCoins(walletId);				
		return new ResponseEntity<>(coinDTOList,HttpStatus.OK);
	}
	
	@GetMapping(value="/{walletId}/coin/{symbol}")
	public ResponseEntity<CoinDTO> getWalletCoin(@PathVariable Integer walletId,@PathVariable String symbol) throws CryptoTrackerException{
		CoinDTO coinDTO = walletService.getCoin(walletId,symbol);				
		return new ResponseEntity<>(coinDTO,HttpStatus.OK);
	}
	
	@PostMapping(value="/{walletId}/addCoin")
	public ResponseEntity<String> addCoin(@PathVariable Integer walletId,@RequestBody CoinDTO coinDTO) throws CryptoTrackerException{
		String message = walletService.addCoin(walletId,coinDTO);				
		return new ResponseEntity<>(message,HttpStatus.OK);
	}

	@PostMapping(value="/createWallet")
	public ResponseEntity<Integer> createWallet(@RequestBody WalletDTO walletDTO) throws CryptoTrackerException{
		Integer walletId = walletService.createWallet(walletDTO);				
		return new ResponseEntity<>(walletId,HttpStatus.OK);
	}
	
	@PutMapping(value="/{walletId}/updateCoin")
	public ResponseEntity<String> updateCoin(@PathVariable Integer walletId,@RequestBody CoinDTO coinDTO) throws CryptoTrackerException{
		String message = walletService.updateCoin(walletId,coinDTO);				
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
			
	@DeleteMapping(value="/{walletId}/coin/{symbol}")
	public ResponseEntity<String> deleteCoin(@PathVariable Integer walletId,@PathVariable String symbol) throws CryptoTrackerException{
		String message = walletService.deleteCoin(walletId,symbol);				
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	@DeleteMapping(value="/deleteWallet/{walletId}")
	public ResponseEntity<String> deleteWallet(@PathVariable Integer walletId) throws CryptoTrackerException{
		String message = walletService.deleteWallet(walletId);				
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
}
