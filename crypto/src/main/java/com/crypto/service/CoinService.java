package com.crypto.service;

import java.util.List;

import com.crypto.dto.CoinDTO;
import com.crypto.dto.ReturnsDTO;
import com.crypto.exception.CryptoTrackerException;

public interface CoinService {

	public List<CoinDTO> getAll();
	public List<CoinDTO> getWalletCoins(Integer walletId) throws CryptoTrackerException;
	public ReturnsDTO getCoinReturns(String symbol, double currentPrice) throws CryptoTrackerException;
	public void addCoinToWallet(CoinDTO coinDTO,Integer walletId) throws CryptoTrackerException;
	public void updateCoin(CoinDTO coinDTO) throws CryptoTrackerException;
	public void deleteCoin(String symbol, Integer walletId) throws CryptoTrackerException;
}
