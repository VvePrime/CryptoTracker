package com.crypto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Coin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer holdingId;
	private String name;	
	private String symbol;
	private double quantity;
	private double costPrice;
	private double value;	
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
	public void setHoldingId(Integer holding_id) {
		this.holdingId = holding_id;
	}
	
}
