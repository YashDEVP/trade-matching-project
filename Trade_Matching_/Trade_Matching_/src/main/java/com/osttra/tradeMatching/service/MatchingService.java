package com.osttra.tradeMatching.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.osttra.tradeMatching.models.MatchedTrade;

public interface MatchingService {
//	 public void createScore(MatchingModel matchScore);
	 public ResponseEntity<?> getMatches(String partyA_tradeRefNum);

	public List<MatchedTrade> getMatchingPartiesAndMatchingScores(String partyA_tradeRefNum);
}
