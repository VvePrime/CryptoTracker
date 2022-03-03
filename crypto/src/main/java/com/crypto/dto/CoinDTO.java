package com.crypto.dto;

import javax.validation.constraints.NotNull;

import com.crypto.entity.Coin;
import com.crypto.entity.Wallet;

public class CoinDTO {

	private Integer holdingId;
	@NotNull
	private String name;
	@NotNull(message="Service.SYMBOL_NULL")
	private String symbol;
	@NotNull(message="Service.QUANTITY_NULL")
	private double quantity;
	@NotNull(message="Service.COSTPRICE_NULL")
	private double costPrice;
	@NotNull(message="Service.VALUE_NULL")
	private double value;
	private WalletDTO walletDTO;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}	
	public Integer getHoldingId() {
		return holdingId;
	}
	public void setHoldingId(Integer holdingId) {
		this.holdingId = holdingId;
	}
	public WalletDTO getWalletDTO() {
		return walletDTO;
	}
	public void setWalletDTO(WalletDTO walletDTO) {
		this.walletDTO = walletDTO;
	}
	
	public Coin createEntity(CoinDTO coinDTO) {
		Coin coin = new Coin();
		coin.setHoldingId(coinDTO.getHoldingId());
		coin.setCostPrice(coinDTO.getCostPrice());
		coin.setName(coinDTO.getName());
		coin.setQuantity(coinDTO.getQuantity());
		coin.setSymbol(coinDTO.getSymbol());
		coin.setValue(coinDTO.getValue());
		if(coinDTO.getWalletDTO()!=null) {
			Wallet wallet = new Wallet();
			wallet.setHolder(coinDTO.getWalletDTO().getHolder());
			wallet.setWalletId(coinDTO.getWalletDTO().getWalletId());
			coin.setWallet(wallet);
		}
		
		return coin;
	}
	
	public static CoinDTO valueOf(Coin coin) {
		CoinDTO coinDTO =new CoinDTO();
		coinDTO.setCostPrice(coin.getCostPrice());
		coinDTO.setName(coin.getName());
		coinDTO.setQuantity(coin.getQuantity());
		coinDTO.setSymbol(coin.getSymbol());
		coinDTO.setValue(coin.getValue());
		coinDTO.setHoldingId(coin.getHoldingId());
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setHolder(coin.getWallet().getHolder());
		walletDTO.setWalletId(coin.getWallet().getWalletId());
		return coinDTO;
	}

}
