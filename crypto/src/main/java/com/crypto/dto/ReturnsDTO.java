package com.crypto.dto;

public class ReturnsDTO {
	public double costPrice;
	public double currentPrice;
	public float returnPercentage;
	public double returnValue;
	public String coinName;
	public String symbol;
	
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}	
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public float getReturnPercentage() {
		return returnPercentage;
	}
	public void setReturnPercentage(float returnPercentage) {
		this.returnPercentage = returnPercentage;
	}
	public double getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(double returnValue) {
		this.returnValue = returnValue;
	}
	

}
