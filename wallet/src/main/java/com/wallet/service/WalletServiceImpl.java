package com.wallet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wallet.dto.CoinDTO;
import com.wallet.dto.CoinDTOList;
import com.wallet.dto.WalletDTO;
import com.wallet.entity.Coin;
import com.wallet.entity.Wallet;
import com.wallet.exception.CryptoTrackerException;
import com.wallet.repository.WalletRepository;
import com.wallet.utility.ErrorInfo;

@Service
@Transactional
public class WalletServiceImpl implements WalletService{

	@Autowired
	WalletRepository walletRepo;
	@Override
	public Integer createWallet(WalletDTO walletDTO)  throws CryptoTrackerException{
		Wallet wallet = walletDTO.createEntity();
		walletRepo.save(wallet);
		Integer walletId = wallet.getWalletId();
		return walletId;
	}

	@Override
	public List<CoinDTO> getWalletCoins(Integer walletId)  throws CryptoTrackerException {
		// TODO Auto-generated method stub
		List<CoinDTO> coins=null;
		String url = "http://localhost:8765/crypto/{walletId}/coins";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> response = restTemplate.getForObject(url, ResponseEntity.class, walletId);
		if(response.getStatusCode()==HttpStatus.OK) {
			Object responseObj = response.getBody();
			if(responseObj != null)
				coins = (List<CoinDTO>)responseObj;
		}
		else{
			Object resposneObj =response.getBody();
			ErrorInfo error = (ErrorInfo)resposneObj;
			throw new CryptoTrackerException(error.getErrorMessage());
		}
		
		return coins;
	}

	public CoinDTO getCoin(Integer walletId, String symbol) throws CryptoTrackerException{
		String url = "http://localhost:8765/crypto/{walletId}/coin/{symbol}";
		RestTemplate restTemplate = new RestTemplate();
		CoinDTO coinDTO = restTemplate.getForObject(url, CoinDTO.class, walletId, symbol);
		return coinDTO;
	}
	@Override
	public String deleteWallet(Integer walletId)  throws CryptoTrackerException{
		walletRepo.deleteById(walletId);		
		return "wallet with wallet id : "+walletId+" deleted.";
	}

	@Override
	public String addCoin(Integer walletId, CoinDTO coinDTO)  throws CryptoTrackerException{
		// TODO Auto-generated method stub
		String url = "http://localhost:8765/crypto/{walletId}/addCoin";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(url, coinDTO, String.class, walletId);		
		return response;
		
	}
	@Override
	public String deleteCoin(Integer walletId, String symbol) throws CryptoTrackerException{
		String url = "http://localhost:8765/crypto/{walletId}/deleteCoin/{symbol}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url, walletId, symbol);
		
		return "Coin Deleted in wallet : "+walletId;
	}
	
	@Override
	public String updateCoin(Integer walletId, CoinDTO coinDTO) throws CryptoTrackerException{
		String url = "http://localhost:8765/crypto/{walletId}/updateInvestment";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url,coinDTO, walletId);
		
		return "Coin updated in wallet : "+walletId;
	}

}
