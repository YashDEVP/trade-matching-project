package com.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.models.PartyValidationModel;


public interface ValidationRepo extends JpaRepository<PartyValidationModel, Long>{

	PartyValidationModel findByPartyAndPartyInstitutionAndPartyFullName(String party,String partyInstitution,String PartyFullName);
	PartyValidationModel findByParty(String party);
}
