package com.crypto.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.dto.CoinDTO;
import com.crypto.dto.ReturnsDTO;
import com.crypto.entity.Coin;
import com.crypto.exception.CryptoTrackerException;
import com.crypto.repository.CoinRepository;

@Service
@Transactional
public class CoinServiceImpl implements CoinService{
	
	@Autowired
	private CoinRepository coinRepository;

	@Override
	public List<CoinDTO> getAllCoins()  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		Iterable<Coin> coins= coinRepository.findAll();
		List<CoinDTO> coinDTOList = new ArrayList<>();
		for (Coin coin:coins) {
			coinDTOList.add(CoinDTO.valueOf(coin));
		}
		return coinDTOList;
	}

	@Override
	public ReturnsDTO getCoinReturns()  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCoin(CoinDTO coinDTO)  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		Coin temp = coinRepository.findBySymbol(coinDTO.getSymbol());
		if(temp !=null) {
			throw new CryptoTrackerException("Service.COIN_EXISTS");
		}
		Coin coin = coinDTO.createEntity(coinDTO);
		coinRepository.save(coin);
		
	}

	@Override
	public void updateCoin(CoinDTO coinDTO) throws CryptoTrackerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCoin(String symbol)  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		
	}

}
