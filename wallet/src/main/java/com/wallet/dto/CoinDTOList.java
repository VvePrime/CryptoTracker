package com.wallet.dto;

import java.util.ArrayList;
import java.util.List;

public class CoinDTOList {
	
	private List<CoinDTO> coinDTOList;
	
	public CoinDTOList() {
		coinDTOList = new ArrayList<>(); 
	}
	
	public List<CoinDTO> getCoinDTOList() {
		return coinDTOList;
	}

	public void setCoinDTOList(List<CoinDTO> coinDTOList) {
		this.coinDTOList = coinDTOList;
	}	
	
}
