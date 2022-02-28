package com.crypto.service;

import java.util.List;

import com.crypto.dto.CoinDTO;
import com.crypto.dto.ReturnsDTO;
import com.crypto.exception.CryptoTrackerException;

public interface CoinService {

	public List<CoinDTO> getAllCoins() throws CryptoTrackerException;
	public ReturnsDTO getCoinReturns() throws CryptoTrackerException;
	public void addCoin(CoinDTO coinDTO) throws CryptoTrackerException;
	public void updateCoin(CoinDTO coinDTO) throws CryptoTrackerException;
	public void deleteCoin(String symbol) throws CryptoTrackerException;
}
