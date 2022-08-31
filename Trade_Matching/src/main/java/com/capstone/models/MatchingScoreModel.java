package com.capstone.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class MatchingScoreModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sNo;
	
	@Column(nullable=false ,length = 20,unique = true)
	@Size(max = 20) @NotBlank
	private String partyTradeRefNum;
	
	@Column(nullable=false ,length = 20,unique = true)
	@Size(max = 20) @NotBlank
	private String counterPartyTradeRefNum;
	
	@Column(nullable=false)
	private int matchScore;
	
	@Column(nullable=false)
	private String status;

	public MatchingScoreModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatchingScoreModel(long sNo, @Size(max = 20) @NotBlank String partyTradeRefNum,
			@Size(max = 20) @NotBlank String counterPartyTradeRefNum, int matchScore, String status) {
		super();
		this.sNo = sNo;
		this.partyTradeRefNum = partyTradeRefNum;
		this.counterPartyTradeRefNum = counterPartyTradeRefNum;
		this.matchScore = matchScore;
		this.status = status;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
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

	public int getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
