package com.osttra.tradeMatching.models;

import java.time.LocalDate;

public class TradeDataInput {

	private String party;
	
	private String tradeId;
	
	private String partyInstitution;
	
	private String counterParty;
	
	private String counterPartyInstitution;
	
	private String partyFullname;
	
	private String counterPartyFullname;
	
	private LocalDate tradeDate;
	
	private LocalDate effectiveDate;
	
	private String InstrumentId;
	
	private double notionalAmount;
	
	private LocalDate maturityDate;
	
	private String currency;
	
	private String seller;
	
	private String buyer;

	public TradeDataInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TradeDataInput(String party, String tradeId, String partyInstitution, String counterParty,
			String counterPartyInstitution, String partyFullname, String counterPartyFullname, LocalDate tradeDate,
			LocalDate effectiveDate, String instrumentId, double notionalAmount, LocalDate maturityDate,
			String currency, String seller, String buyer) {
		super();
		this.party = party;
		this.tradeId = tradeId;
		this.partyInstitution = partyInstitution;
		this.counterParty = counterParty;
		this.counterPartyInstitution = counterPartyInstitution;
		this.partyFullname = partyFullname;
		this.counterPartyFullname = counterPartyFullname;
		this.tradeDate = tradeDate;
		this.effectiveDate = effectiveDate;
		InstrumentId = instrumentId;
		this.notionalAmount = notionalAmount;
		this.maturityDate = maturityDate;
		this.currency = currency;
		this.seller = seller;
		this.buyer = buyer;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getPartyInstitution() {
		return partyInstitution;
	}

	public void setPartyInstitution(String partyInstitution) {
		this.partyInstitution = partyInstitution;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getCounterPartyInstitution() {
		return counterPartyInstitution;
	}

	public void setCounterPartyInstitution(String counterPartyInstitution) {
		this.counterPartyInstitution = counterPartyInstitution;
	}

	public String getPartyFullname() {
		return partyFullname;
	}

	public void setPartyFullname(String partyFullname) {
		this.partyFullname = partyFullname;
	}

	public String getCounterPartyFullname() {
		return counterPartyFullname;
	}

	public void setCounterPartyFullname(String counterPartyFullname) {
		this.counterPartyFullname = counterPartyFullname;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getInstrumentId() {
		return InstrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		InstrumentId = instrumentId;
	}

	public double getNotionalAmount() {
		return notionalAmount;
	}

	public void setNotionalAmount(double notionalAmount) {
		this.notionalAmount = notionalAmount;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	
	
}
