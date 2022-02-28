package com.crypto.repository;

import org.springframework.data.repository.CrudRepository;

import com.crypto.entity.Coin;

public interface CoinRepository extends CrudRepository<Coin,Integer>{

	public Coin findBySymbol(String symbol);
	public void deleteBySymbol(String symbol);
}
