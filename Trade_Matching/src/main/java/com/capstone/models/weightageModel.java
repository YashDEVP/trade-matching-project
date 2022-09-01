package com.capstone.models;

public class weightageModel {

	private int tradeDatePercent=10;
	
	private int effectiveDatePercent=10;
	
	private int maturityDatePercent=8;
	
	private int instrumentIdPercent=18;
	
	private int notionalAmountPercent=10;
	
	private int currencyPercent=8;
	
	private int sellerPercent=18;
	
	private int buyerPercent=18;

	public weightageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public weightageModel(int tradeDatePercent, int effectiveDatePercent, int maturityDatePercent,
			int instrumentIdPercent, int notionalAmountPercent, int currencyPercent, int sellerPercent,
			int buyerPercent) {
		super();
		this.tradeDatePercent = tradeDatePercent;
		this.effectiveDatePercent = effectiveDatePercent;
		this.maturityDatePercent = maturityDatePercent;
		this.instrumentIdPercent = instrumentIdPercent;
		this.notionalAmountPercent = notionalAmountPercent;
		this.currencyPercent = currencyPercent;
		this.sellerPercent = sellerPercent;
		this.buyerPercent = buyerPercent;
	}

	public int getTradeDatePercent() {
		return tradeDatePercent;
	}

	public void setTradeDatePercent(int tradeDatePercent) {
		this.tradeDatePercent = tradeDatePercent;
	}

	public int getEffectiveDatePercent() {
		return effectiveDatePercent;
	}

	public void setEffectiveDatePercent(int effectiveDatePercent) {
		this.effectiveDatePercent = effectiveDatePercent;
	}

	public int getMaturityDatePercent() {
		return maturityDatePercent;
	}

	public void setMaturityDatePercent(int maturityDatePercent) {
		this.maturityDatePercent = maturityDatePercent;
	}

	public int getInstrumentIdPercent() {
		return instrumentIdPercent;
	}

	public void setInstrumentIdPercent(int instrumentIdPercent) {
		this.instrumentIdPercent = instrumentIdPercent;
	}

	public int getNotionalAmountPercent() {
		return notionalAmountPercent;
	}

	public void setNotionalAmountPercent(int notionalAmountPercent) {
		this.notionalAmountPercent = notionalAmountPercent;
	}

	public int getCurrencyPercent() {
		return currencyPercent;
	}

	public void setCurrencyPercent(int currencyPercent) {
		this.currencyPercent = currencyPercent;
	}

	public int getSellerPercent() {
		return sellerPercent;
	}

	public void setSellerPercent(int sellerPercent) {
		this.sellerPercent = sellerPercent;
	}

	public int getBuyerPercent() {
		return buyerPercent;
	}

	public void setBuyerPercent(int buyerPercent) {
		this.buyerPercent = buyerPercent;
	}
	
	
} 
