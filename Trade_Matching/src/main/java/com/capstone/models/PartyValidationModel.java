package com.capstone.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class PartyValidationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(columnDefinition = "LONG DEFAULT '1'")
	private Long id;
	
	@Column(nullable=false ,columnDefinition = "varchar(20)")
	@Min(value=2)
	@Max(value=20)
	private String partyInstitution;
	
	@Column(nullable=false ,columnDefinition = "varchar(20)",unique = true)
	@Min(value=2)
	@Max(value=20)
	private String party;
	
	@Column(nullable = false,columnDefinition = "varchar(200)",unique = true)
	@Min(value=2)
	@Max(value=200)
	private String partyFullName;

	
	
	
	public PartyValidationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public PartyValidationModel(Long id, @Min(2) @Max(20) String partyInstitution, @Min(2) @Max(20) String party,
			@Min(2) @Max(200) String partyFullName) {
		super();
		this.id = id;
		this.partyInstitution = partyInstitution;
		this.party = party;
		this.partyFullName = partyFullName;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartyInstitution() {
		return partyInstitution;
	}

	public void setPartyInstitution(String partyInstitution) {
		this.partyInstitution = partyInstitution;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getPartyFullName() {
		return partyFullName;
	}

	public void setPartyFullName(String partyFullName) {
		this.partyFullName = partyFullName;
	}



	@Override
	public String toString() {
		return "PartyValidationModel [id=" + id + ", partyInstitution=" + partyInstitution + ", party=" + party
				+ ", partyFullName=" + partyFullName + "]";
	}

	
	
}
