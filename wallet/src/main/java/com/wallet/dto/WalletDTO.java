package com.wallet.dto;

import com.wallet.entity.Wallet;

public class WalletDTO {
	
	private Integer walletId;
	private String holder;
	
	public Integer getWalletId() {
		return walletId;
	}
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	
	public Wallet createEntity() {
		Wallet wallet = new Wallet();
		wallet.setHolder(getHolder());
		return wallet;
	}
	
	public static WalletDTO valueOf(Wallet wallet) {
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setHolder(wallet.getHolder());
		walletDTO.setWalletId(walletDTO.getWalletId());
		return walletDTO;
	}
	
}
