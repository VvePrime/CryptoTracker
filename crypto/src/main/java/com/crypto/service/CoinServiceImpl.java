package com.crypto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.dto.CoinDTO;
import com.crypto.dto.ReturnsDTO;
import com.crypto.entity.Coin;
import com.crypto.entity.Wallet;
import com.crypto.exception.CryptoTrackerException;
import com.crypto.repository.CoinRepository;
import com.crypto.repository.WalletRepository;

@Service
@Transactional
public class CoinServiceImpl implements CoinService{
	
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private CoinRepository coinRepository;

	@Override
	public List<CoinDTO> getAll() {
		Iterable<Coin> coins = coinRepository.findAll();
		List<CoinDTO> coinDTOList = new ArrayList<>();
		for (Coin coin:coins) {
			coinDTOList.add(CoinDTO.valueOf(coin));
		}
		return coinDTOList;
	}
	
	@Override
	public List<CoinDTO> getWalletCoins(Integer walletId)  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		Iterable<Coin> coins= coinRepository.findByWalletWalletId(walletId);
		List<CoinDTO> coinDTOList = new ArrayList<>();
		for (Coin coin:coins) {
			coinDTOList.add(CoinDTO.valueOf(coin));
		}
		return coinDTOList;
	}

	@Override
	public ReturnsDTO getCoinReturns(String symbol, double currentPrice)  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		Coin coin = coinRepository.findBySymbol(symbol);
		if(coin == null) {
			throw new CryptoTrackerException("Service.COIN_DOESNOT_EXIST");
		}
		ReturnsDTO returnsDTO = new ReturnsDTO();
		returnsDTO.setCoinName(coin.getName());
		returnsDTO.setSymbol(symbol);
		returnsDTO.setCostPrice(coin.getCostPrice());
		returnsDTO.setCurrentPrice(currentPrice);
		returnsDTO.setReturnPercentage((float)((currentPrice - coin.getCostPrice())/coin.getCostPrice() )*100);
		returnsDTO.setReturnValue((currentPrice - coin.getCostPrice() )*coin.getQuantity());
		return returnsDTO;
	}

	@Override
	public void addCoinToWallet(CoinDTO coinDTO,Integer walletId)  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
		if(!optionalWallet.isPresent()) {
			throw new CryptoTrackerException("Service.Wallet_DOESNOT_EXIST");
		}
		List<Coin> coins= coinRepository.findByWalletWalletId(walletId);
		if(!coins.isEmpty()) {
			for(Coin coin: coins) {
				if(coin.getSymbol().equals(coinDTO.getSymbol()))
						throw new CryptoTrackerException("Service.COIN_EXISTS");
			}
			
		}
		Coin coin = coinDTO.createEntity(coinDTO);
		coin.setWallet(optionalWallet.get());
		coinRepository.save(coin);
		
	}

	@Override
	public void updateCoin(CoinDTO coinDTO) throws CryptoTrackerException {
		// TODO Auto-generated method stub
		Coin coin = coinRepository.findBySymbol(coinDTO.getSymbol());
		if(coin == null) {
			throw new CryptoTrackerException("Service.COIN_DOESNOT_EXIST");
		}
		coin.setCostPrice(coinDTO.getCostPrice());
		coin.setQuantity(coinDTO.getQuantity());
		coin.setValue(coinDTO.getValue());
		coinRepository.save(coin);
		
	}

	@Override
	public void deleteCoin(String symbol, Integer walletId)  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		List<Coin> coins= coinRepository.findByWalletWalletId(walletId);
		boolean present = false;
		Integer coinId=0;
		if(!coins.isEmpty()) {
			for(Coin coin: coins) {
				if(coin.getSymbol().equals(symbol) ) {
					present = true;
					coinId=coin.getHoldingId();
				}						
			}
			
		}
		if(!present) {
			throw new CryptoTrackerException("Service.COIN_DOESNOT_EXIST");
		}
		coinRepository.deleteById(coinId);
	}
	

	

}
