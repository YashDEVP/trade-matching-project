package com.osttra.tradeMatching.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.matchAlgo.MatchingScore;
import com.osttra.tradeMatching.models.MatchedTrade;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.repo.MatchRepository;
import com.osttra.tradeMatching.repo.TradeRepo;

@Service
public class MatchingServiceImpl implements MatchingService {

	@Autowired
	public MatchRepository matchRepo;

	@Autowired
	TradeRepo tradeRepo;
	
	@Override
	public ResponseEntity<HashMap<String, Integer>> getMatches(String partyA_tradeRefNum) {

		ArrayList<TradeData> tradesList = new ArrayList<TradeData>();
		System.out.println(partyA_tradeRefNum);
		TradeData tradeA = tradeRepo.findByTradeRefNum(partyA_tradeRefNum);
		System.out.println("chal raha hu bhai me kahe uar jaa ke dekh");
		System.out.println("TRADE A-----> : " + tradeA.getTradeRefNum());

		String counterParty = tradeA.getCounterParty();
		System.out.println("Counterparty : " + counterParty);

		tradeRepo.findByPartyAndStatus(counterParty, "Unconfirmed").forEach(tradesList::add);
		System.out.println("Getting all counter unconfirmed trades in list:---------------");
		for (TradeData t : tradesList)
			System.out.println(t.getTradeRefNum() + "    :" + t.getPartyFullname());

		if (tradesList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		HashMap<String, Integer> getScores = new HashMap<String, Integer>();
		MatchingScore matchingScore = new MatchingScore();
//			int flagForStopingIteration = 0;

		for (TradeData cpTrade : tradesList) {

			int percent = matchingScore.calculateMatchingScore(tradeA, cpTrade);
			getScores.put(cpTrade.getTradeRefNum(), percent);
			System.out.println("Matching Score between "+tradeA.getTradeRefNum()+cpTrade.getTradeRefNum()+percent);

			MatchedTrade newMatchScore = new MatchedTrade();
			newMatchScore.setA_tradeRefNum(tradeA.getTradeRefNum());
			newMatchScore.setB_tradeRefNum(cpTrade.getTradeRefNum());
			newMatchScore.setMatchingScore(percent);

			if (percent < 100) {
				int lack = 100 - percent;
				newMatchScore.setStatusAfterMatch("Unconfirmed by " + lack + "%.");
				matchRepo.save(newMatchScore);
			} else if (percent == 100) {
				// Stop other matchings and Confirm both trades A, B in TradeData.

				System.out.println("Hey!  100% match found.");

				tradeA.setVersion(tradeA.getVersion() + 1);
				tradeA.setStatus("Confirmed");
				System.out.println("Hurray! " + tradeA.getTradeRefNum() + "  got  " + tradeA.getStatus()	+ " & upgraded version = " + tradeA.getVersion());

				cpTrade.setVersion(cpTrade.getVersion() + 1);
				cpTrade.setStatus("Confirmed");
				System.out.println("Hurray! " + cpTrade.getTradeRefNum() + " got " + cpTrade.getStatus()	+ " & upgraded version = " + cpTrade.getVersion());
				
				tradeRepo.save(tradeA);
				tradeRepo.save(cpTrade);
				break;
			}

		}
		System.out.println("SCORES--------->" + getScores);
		if (getScores.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(getScores, HttpStatus.OK);

	}

	@Override
	public List<MatchedTrade> getMatchingPartiesAndMatchingScores(String partyA_tradeRefNum) {
		// SbiGurgaon002
		return matchRepo.findAllByA_tradeRefNum(partyA_tradeRefNum);
	}

}
