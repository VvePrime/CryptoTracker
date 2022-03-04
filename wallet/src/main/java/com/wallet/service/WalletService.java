package com.wallet.service;

import java.util.List;

import com.wallet.dto.CoinDTO;
import com.wallet.dto.WalletDTO;
import com.wallet.exception.CryptoTrackerException;

public interface WalletService {
	
	public CoinDTO getCoin(Integer walletId, String symbol) throws CryptoTrackerException;
	public Integer createWallet(WalletDTO walletDTO) throws CryptoTrackerException;
	public List<CoinDTO> getWalletCoins(Integer walletId) throws CryptoTrackerException;
	public String deleteWallet(Integer walletId) throws CryptoTrackerException;
	public String addCoin(Integer walletId, CoinDTO coinDTO) throws CryptoTrackerException;
	public String deleteCoin(Integer walletId, String symbol) throws CryptoTrackerException;
	public String updateCoin(Integer walletId, CoinDTO coinDTO) throws CryptoTrackerException;

}
