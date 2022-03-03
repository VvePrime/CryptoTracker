package com.crypto.repository;
import org.springframework.data.repository.CrudRepository;

import com.crypto.entity.Wallet;

public interface WalletRepository extends CrudRepository<Wallet,Integer>{

	
}
