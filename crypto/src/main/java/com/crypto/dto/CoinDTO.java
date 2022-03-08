package com.crypto.dto;

import javax.validation.constraints.NotNull;

import com.crypto.entity.Coin;

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
	@NotNull
	private Integer walletId;
	
	public Integer getWalletId() {
		return walletId;
	}
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
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

	
	public Coin createEntity(CoinDTO coinDTO) {
		Coin coin = new Coin();
		coin.setHoldingId(coinDTO.getHoldingId());
		coin.setCostPrice(coinDTO.getCostPrice());
		coin.setName(coinDTO.getName());
		coin.setQuantity(coinDTO.getQuantity());
		coin.setSymbol(coinDTO.getSymbol());
		coin.setValue(coinDTO.getValue());
		coin.setWalletId(coinDTO.getWalletId());
		
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
		coinDTO.setWalletId(coin.getWalletId());
		return coinDTO;
	}

}
