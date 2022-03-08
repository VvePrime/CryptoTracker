package com.crypto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crypto.entity.Coin;

public interface CoinRepository extends CrudRepository<Coin,Integer>{

	public List<Coin> findByWalletId(Integer walletId);
	public Coin findBySymbol(String symbol);
	public void deleteBySymbol(String symbol);
}
