package com.osttra.tradeMatching.service;

import java.awt.Insets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.exception.TradeNotFoundException;

import com.osttra.tradeMatching.controller.MatchingController;

import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.repo.ValidationRepo;
import com.osttra.tradeMatching.utils.TradeValidation;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepo tradeRepository;

	@Autowired
	private ValidationRepo validationRepository;

	@Autowired
	private TradeValidation validTrade;

	@Autowired
	MatchingController MatchingController;

	@Override
	public TradeData getTradeByTradeRefNum(String tradeRefNum) throws TradeNotFoundException {
		// TODO Auto-generated method stub
		TradeData showTrade = tradeRepository.findByTradeRefNum(tradeRefNum);
		if (showTrade != null) {
			return showTrade;
			// return new ResponseEntity<>("Invalid TradeRefNumber Please enter valid
			// TradeRefNumber ", HttpStatus.NOT_FOUND);
		}
		throw new TradeNotFoundException(Constants.noTradeReferenceNumber + "" + tradeRefNum);
		
	}

	@Override
	public ResponseEntity<?> insertTrade(TradeDataInput newTrade) {

		TradeData insertedTradeData = new TradeData();

		java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
		System.out.println(currentDate);
		insertedTradeData.setParty(newTrade.getParty());
		System.out.println(insertedTradeData.getConfirmationTimestamp());
		System.out.println("dxhgbbbxbxbbxbxbxbbxb");
		insertedTradeData.setPartyFullname(newTrade.getPartyFullname());
		insertedTradeData.setPartyInstitution(newTrade.getPartyInstitution());
		insertedTradeData.setCounterParty(newTrade.getCounterParty());
		insertedTradeData.setCounterPartyFullname(newTrade.getCounterPartyFullname());
		insertedTradeData.setCounterPartyInstitution(newTrade.getCounterPartyInstitution());
		insertedTradeData.setBuyer(newTrade.getBuyer());
		insertedTradeData.setSeller(newTrade.getSeller());
		insertedTradeData.setInstrumentId(newTrade.getInstrumentId());
		insertedTradeData.setNotionalAmount(newTrade.getNotionalAmount());
		insertedTradeData.setCurrency(newTrade.getCurrency());
		insertedTradeData.setTradeDate(newTrade.getTradeDate());
		insertedTradeData.setEffectiveDate(newTrade.getEffectiveDate());
		insertedTradeData.setMaturityDate(newTrade.getMaturityDate());
		insertedTradeData.setTradeId(newTrade.getTradeId());
		System.out.println("uskslsow...........");
		insertedTradeData.setConfirmationTimestamp(currentDate);
		insertedTradeData.setCreationTimestamp(currentDate);
		insertedTradeData.setVersionTimestamp(currentDate);
		insertedTradeData.setStatus("unconfirmed");
		insertedTradeData.setTradeRefNum(newTrade.getParty(), newTrade.getTradeId());
		insertedTradeData.setVersion(1);
		System.out.println("hsgdjdoehwms...........");
		System.out.println(insertedTradeData.getTradeId());
		System.out.println(insertedTradeData.getConfirmationTimestamp());
		System.out.println(insertedTradeData.getTradeRefNum());
		System.out.println(insertedTradeData.getStatus());
		System.out.println(insertedTradeData.getVersion());

		String isValid = validTrade.checkValidTrade(insertedTradeData);
		if (isValid.equals(Constants.MESSAGE1)) {
			System.out.println("saved");
			ResponseEntity.of(Optional.of(tradeRepository.save(insertedTradeData)));
		}
		// ResponseEntity.of(Optional.of(tradeRepository.save(newTrade)));

		// Calling Matcher
		String PartyA_tradeRefNum = insertedTradeData.getTradeRefNum();
		ResponseEntity<?> ob = MatchingController.getMatches(PartyA_tradeRefNum);

		if (ob.getBody() == null) {
			System.out.println(
					"New Trade Created with TradeRefNum " + PartyA_tradeRefNum + " --> No Matching Trades Found");
			return new ResponseEntity<>(insertedTradeData, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(ob, HttpStatus.FOUND);

	}

	@Override
	public ResponseEntity<?> getAllTrade() {
		List<TradeData> allTrade = tradeRepository.findAll();
		if (allTrade.isEmpty()) {
			return new ResponseEntity<>(Constants.ERROR7, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.of(Optional.of(allTrade));
	}

	@Override
	public List<TradeData> getTradesByParty(String party) throws TradeNotFoundException {

		List<TradeData> trades = tradeRepository.findByParty(party);
		if (!trades.isEmpty()) {
			return trades;
		} else {
			throw new TradeNotFoundException(Constants.noPartiesExist + "" + party);
		}

	}

}
