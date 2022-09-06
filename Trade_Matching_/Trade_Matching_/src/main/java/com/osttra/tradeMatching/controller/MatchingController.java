package com.osttra.tradeMatching.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.osttra.tradeMatching.models.MatchedTrade;
import com.osttra.tradeMatching.service.MatchingService;

import io.swagger.annotations.*;

@RestController
@RequestMapping("/osttra/match")

public class MatchingController {

	@Autowired
	private MatchingService matchService;

	@ApiOperation(value = "Get all matches of a party w with scores.")
	@GetMapping("/Matches")
	public ResponseEntity<?> getMatches(@RequestParam(required = true) String PartyA_tradeRefNum) {
		System.out.println("MATCHING_CONTROLLER: PARTY_A" + PartyA_tradeRefNum);
		return matchService.getMatches(PartyA_tradeRefNum);
	}

	@ApiOperation(value = "Serach for a party &traderefNumber to Get all the matching parties with its matching scores.")
	@GetMapping("/getMatchingPartiesAndMatchingScores")
	public List<MatchedTrade> getMatchingPartiesAndMatchingScores(@RequestParam(required = false) String PartyA,
			@RequestParam(required = true) String PartyA_tradeRefNum) {
		System.out.println("Searching for a trade's matching parties and Scores:" + PartyA + PartyA_tradeRefNum);

		return matchService.getMatchingPartiesAndMatchingScores(PartyA_tradeRefNum);
	}

}
