package com.capstone.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MatchingScoreModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sNo;
	
	private String partyTradeRefNum;

	private String counterPartyTradeRefNum;
	
	private int matchingPercent;
	
	private String status;

	public MatchingScoreModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatchingScoreModel(Long sNo, String partyTradeRefNum, String counterPartyTradeRefNum, int matchingPercent,
			String status) {
		super();
		this.sNo = sNo;
		this.partyTradeRefNum = partyTradeRefNum;
		this.counterPartyTradeRefNum = counterPartyTradeRefNum;
		this.matchingPercent = matchingPercent;
		this.status = status;
	}

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}

	public String getPartyTradeRefNum() {
		return partyTradeRefNum;
	}

	public void setPartyTradeRefNum(String partyTradeRefNum) {
		this.partyTradeRefNum = partyTradeRefNum;
	}

	public String getCounterPartyTradeRefNum() {
		return counterPartyTradeRefNum;
	}

	public void setCounterPartyTradeRefNum(String counterPartyTradeRefNum) {
		this.counterPartyTradeRefNum = counterPartyTradeRefNum;
	}

	public int getMatchingPercent() {
		return matchingPercent;
	}

	public void setMatchingPercent(int matchingPercent) {
		this.matchingPercent = matchingPercent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
