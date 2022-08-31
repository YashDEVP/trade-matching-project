package com.capstone.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PercentageMatchingModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sNo;
	
	@Column(columnDefinition = "INT DEFAULT '10'")
	private int tradeDatePercen;
	
	@Column(columnDefinition = "INT DEFAULT '10'")
	private int effectiveDatePercen;
	
	@Column(columnDefinition = "INT DEFAULT '18'")
	private int instrumentIdPercen;
	
	@Column(columnDefinition = "INT DEFAULT '10'")
	private int notonalAmountPercen;
	
	@Column(columnDefinition = "INT DEFAULT '8'")
	private int maturityDatePercen;
	
	@Column(columnDefinition = "INT DEFAULT '8'")
	private int currencyPercen;
	
	@Column(columnDefinition = "INT DEFAULT '18'")
	private int sellerPercen;
	
	@Column(columnDefinition = "INT DEFAULT '18'")
	private int buyerPercen;

	public PercentageMatchingModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PercentageMatchingModel(int sNo, int tradeDatePercen, int effectiveDatePercen, int instrumentIdPercen,
			int notonalAmountPercen, int maturityDatePercen, int currencyPercen, int sellerPercen, int buyerPercen) {
		super();
		this.sNo = sNo;
		this.tradeDatePercen = tradeDatePercen;
		this.effectiveDatePercen = effectiveDatePercen;
		this.instrumentIdPercen = instrumentIdPercen;
		this.notonalAmountPercen = notonalAmountPercen;
		this.maturityDatePercen = maturityDatePercen;
		this.currencyPercen = currencyPercen;
		this.sellerPercen = sellerPercen;
		this.buyerPercen = buyerPercen;
	}

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public int getTradeDatePercen() {
		return tradeDatePercen;
	}

	public void setTradeDatePercen(int tradeDatePercen) {
		this.tradeDatePercen = tradeDatePercen;
	}

	public int getEffectiveDatePercen() {
		return effectiveDatePercen;
	}

	public void setEffectiveDatePercen(int effectiveDatePercen) {
		this.effectiveDatePercen = effectiveDatePercen;
	}

	public int getInstrumentIdPercen() {
		return instrumentIdPercen;
	}

	public void setInstrumentIdPercen(int instrumentIdPercen) {
		this.instrumentIdPercen = instrumentIdPercen;
	}

	public int getNotonalAmountPercen() {
		return notonalAmountPercen;
	}

	public void setNotonalAmountPercen(int notonalAmountPercen) {
		this.notonalAmountPercen = notonalAmountPercen;
	}

	public int getMaturityDatePercen() {
		return maturityDatePercen;
	}

	public void setMaturityDatePercen(int maturityDatePercen) {
		this.maturityDatePercen = maturityDatePercen;
	}

	public int getCurrencyPercen() {
		return currencyPercen;
	}

	public void setCurrencyPercen(int currencyPercen) {
		this.currencyPercen = currencyPercen;
	}

	public int getSellerPercen() {
		return sellerPercen;
	}

	public void setSellerPercen(int sellerPercen) {
		this.sellerPercen = sellerPercen;
	}

	public int getBuyerPercen() {
		return buyerPercen;
	}

	public void setBuyerPercen(int buyerPercen) {
		this.buyerPercen = buyerPercen;
	}
	
	
}
