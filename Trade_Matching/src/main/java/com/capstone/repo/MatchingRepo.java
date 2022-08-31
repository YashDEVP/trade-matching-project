package com.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.models.MatchingScoreModel;
import com.capstone.models.PartyValidationModel;

@Repository
public interface MatchingRepo  extends JpaRepository<PartyValidationModel, Long> {

	void save(MatchingScoreModel calWeight);

	
	
}
