package com.wallet.repository;

import org.springframework.data.repository.CrudRepository;
import com.wallet.entity.Wallet;

public interface WalletRepository extends CrudRepository<Wallet,Integer>{

}
