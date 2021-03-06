package com.wallet.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wallet.dto.CoinDTO;
import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.exception.CryptoTrackerException;
import com.wallet.repository.WalletRepository;

@Service
@Transactional
public class WalletServiceImpl implements WalletService{

	@Autowired
	WalletRepository walletRepo;
	@Autowired
	Environment environment;
	@Override
	public Integer createWallet(WalletDTO walletDTO)  throws CryptoTrackerException{
		Wallet wallet = walletDTO.createEntity();
		walletRepo.save(wallet);
		Integer walletId = wallet.getWalletId();
		return walletId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoinDTO> getWalletCoins(Integer walletId)  throws CryptoTrackerException {
		// TODO Auto-generated method stub
		List<CoinDTO> coins=null;
		Optional<Wallet> optionalWallet = walletRepo.findById(walletId);
		if(!optionalWallet.isPresent()) {
			throw new CryptoTrackerException("Service.WALLET_DOESNOT_EXIST");
		}
		String url = environment.getProperty("CoinUri")+"/{walletId}/coins";
		RestTemplate restTemplate = new RestTemplate();
		//ResponseEntity<?> response = restTemplate.getForObject(url, ResponseEntity.class, walletId);
		//ResponseEntity<?> response = restTemplate.getForEntity(url,ResponseEntity.class,walletId);
		ResponseEntity<List<CoinDTO>> response = restTemplate.exchange(url,HttpMethod.GET,	null,new ParameterizedTypeReference<List<CoinDTO>>(){},walletId);
		
			Object responseObj = response.getBody();
			if(responseObj != null)
				coins = (List<CoinDTO>)responseObj;
		
		
		return coins;
	}

	public CoinDTO getCoin(Integer walletId, String symbol) throws CryptoTrackerException{
		Optional<Wallet> optionalWallet = walletRepo.findById(walletId);
		if(!optionalWallet.isPresent()) {
			throw new CryptoTrackerException("Service.WALLET_DOESNOT_EXIST");
		}
		CoinDTO coinDTO = null;
		String url = environment.getProperty("CoinUri")+"/{walletId}/coin/{symbol}";
		RestTemplate restTemplate = new RestTemplate();		
		coinDTO = restTemplate.getForObject(url, CoinDTO.class, walletId, symbol);
		return coinDTO;
	}
	@Override
	public String deleteWallet(Integer walletId)  throws CryptoTrackerException{
		Optional<Wallet> optionalWallet = walletRepo.findById(walletId);
		if(!optionalWallet.isPresent()) {
			throw new CryptoTrackerException("Service.WALLET_DOESNOT_EXIST");
		}
		walletRepo.deleteById(walletId);		
		return "wallet with wallet id : "+walletId+" deleted.";
	}

	@Override
	public String addCoin(Integer walletId, CoinDTO coinDTO)  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		Optional<Wallet> optionalWallet = walletRepo.findById(walletId);
		if(!optionalWallet.isPresent()) {
			throw new CryptoTrackerException("Service.WALLET_DOESNOT_EXIST");
		}
		String url = environment.getProperty("CoinUri")+"/{walletId}/addCoin";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(url, coinDTO, String.class, walletId);		
		return response;
		
	}
	@Override
	public String deleteCoin(Integer walletId, String symbol) throws CryptoTrackerException{
		Optional<Wallet> optionalWallet = walletRepo.findById(walletId);
		if(!optionalWallet.isPresent()) {
			throw new CryptoTrackerException("Service.WALLET_DOESNOT_EXIST");
		}
		String url = environment.getProperty("CoinUri")+"/{walletId}/deleteCoin/{symbol}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url, walletId, symbol);
		
		return "Coin Deleted in wallet : "+walletId;
	}
	
	@Override
	public String updateCoin(Integer walletId, CoinDTO coinDTO) throws CryptoTrackerException{
		Optional<Wallet> optionalWallet = walletRepo.findById(walletId);
		if(!optionalWallet.isPresent()) {
			throw new CryptoTrackerException("Service.WALLET_DOESNOT_EXIST");
		}
		String url = environment.getProperty("CoinUri")+"/{walletId}/updateInvestment";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url,coinDTO, walletId);
		
		return "Coin updated in wallet : "+walletId;
	}

}
