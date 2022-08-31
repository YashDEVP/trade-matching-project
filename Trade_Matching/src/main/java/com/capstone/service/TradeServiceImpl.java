package com.capstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.models.MatchingScoreModel;
import com.capstone.models.PartyValidationModel;
import com.capstone.models.PercentageMatchingModel;
import com.capstone.models.TradeDataModel;
import com.capstone.repo.MatchingRepo;
import com.capstone.repo.TradeRepo;
import com.capstone.repo.ValidationRepo;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepo tradeRepository;

	@Autowired
	private ValidationRepo ValidationRepository;
	
	@Autowired
	private MatchingRepo matchingRepository;

	@Override
	public ResponseEntity<List<TradeDataModel>> getAllTrade() {
		// TODO Auto-generated method stub
		List<TradeDataModel> allTrade=tradeRepository.findAll();
		if(allTrade.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		 return ResponseEntity.of(Optional.of(allTrade));
	}

	public ResponseEntity<TradeDataModel> insertTrade(TradeDataModel newTrade) {
		
		if(checkValidTrade(newTrade)) {
			List<TradeDataModel> matchedTrade=tradeRepository.findByPartyAndCounterPartyAndStatus(
					newTrade.getCounterParty(), 
					newTrade.getParty(),
					newTrade.getStatus());
			
			calculateMatchingScore(newTrade,matchedTrade);
			return ResponseEntity.of(Optional.of(tradeRepository.save(newTrade)));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	
	@Override
	public ResponseEntity<List<TradeDataModel>> getTradeByPartyStatus(String party, String status) {
		
		//Search By Counterparty and status
		
		List<TradeDataModel> allTrade=tradeRepository.findByPartyAndStatus(party, status);
		if(allTrade.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(allTrade));
	}

	
	@Override
	public ResponseEntity<TradeDataModel> getTradeByTradeRefNum(String tradeRefNum) {
		TradeDataModel showTrade=tradeRepository.findByTradeRefNum(tradeRefNum);
		if(showTrade!=null) {
			return ResponseEntity.of(Optional.of(showTrade)); 
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	
	@Override
	public ResponseEntity<TradeDataModel> updateTradeByTradeRefNum(String tradeRefNum, TradeDataModel newTradeData) {
		//Check if both have same TradeRefNum,and new td should be unconfirmed
		TradeDataModel existingTradeData= tradeRepository.findByTradeRefNum(tradeRefNum);
		if(existingTradeData!=null) {
			// Insert new VersionTimesStampp  at insertion
			BeanUtils.copyProperties(newTradeData, existingTradeData);
			newTradeData.setVersion(existingTradeData.getVersion()+1);
			return ResponseEntity.of(Optional.of(tradeRepository.save(newTradeData)));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
}

	public void calculateMatchingScore(TradeDataModel newTrade,List<TradeDataModel> matchedTrade) {
		System.out.println("****************************8calling calculateMatchingScore");
		PercentageMatchingModel weight=new PercentageMatchingModel();
		MatchingScoreModel calWeight=new MatchingScoreModel();
		int calculatedWeight=0;
		if(matchedTrade.isEmpty()) {
			System.out.println("****************************empty   ldit");
		}
		for(TradeDataModel td:matchedTrade) {
			System.out.println("****************************8calling for lllll  calculateMatchingScore");
			if(newTrade.getTradeDate().compareTo(td.getTradeDate())==0) {
				calculatedWeight=calculatedWeight+((PercentageMatchingModel) weight).getTradeDatePercen();
			}
			if(newTrade.getEffectiveDate().compareTo(td.getEffectiveDate())==0) {
				calculatedWeight=calculatedWeight+weight.getEffectiveDatePercen();
			}
			if(newTrade.getMaturityDate().compareTo(td.getMaturityDate())==0) {
				calculatedWeight=calculatedWeight+weight.getMaturityDatePercen();
			}
			if(newTrade.getInstrumentId().equals(td.getInstrumentId())) {
				calculatedWeight+=weight.getInstrumentIdPercen();
			}
			if(newTrade.getNotionalAmount()==td.getNotionalAmount()) {
				calculatedWeight+=weight.getNotonalAmountPercen();
			}
			if(newTrade.getCurrency().equals(td.getCurrency())) {
				calculatedWeight+=weight.getCurrencyPercen();
			}
			if(newTrade.getBuyer().equals(td.getBuyer())) {
				calculatedWeight+=weight.getBuyerPercen();
			}
			if(newTrade.getSeller().equals(td.getSeller())) {
			calculatedWeight+=weight.getSellerPercen();	
			}
			
			calWeight.setMatchScore(calculatedWeight);
			calWeight.setPartyTradeRefNum(newTrade.getTradeRefNum());
			calWeight.setCounterPartyTradeRefNum(td.getTradeRefNum());
			
			if(calculatedWeight==100) {
				calWeight.setStatus("Confirmed");
			}
			calWeight.setStatus("UnConfirmed");
					
			System.out.println("****************************8calling calculateMatchingScore");
			matchingRepository.save(calWeight);
			
		}
		
	}
	
	public Boolean checkValidTrade(TradeDataModel newTrade) {
		int checkDate = 0;
		int validTRN = 0;
		int validBuyerSeller = 0;
		int validPartyAndCParty = 0;
		int validBuyerSellerparty = 0;
		newTrade.setTradeRefNum(newTrade.getParty(), newTrade.getTradeId());
		TradeDataModel td1 = tradeRepository.findByTradeRefNum(newTrade.getTradeRefNum());
		newTrade.setVersion(1);
		newTrade.setStatus("Unconfirmed");
		// Insert new TimesStampp in Td at insertion

		// party validation
		PartyValidationModel pvm = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(newTrade.getParty(),
				newTrade.getPartyInstitution(), newTrade.getPartyFullname());
		PartyValidationModel pvm1 = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(
				newTrade.getCounterParty(), newTrade.getCounterPartyInstitution(), newTrade.getCounterPartyFullname());
		
		if (pvm != null && pvm1 != null) {
			if (!newTrade.getParty().equals(newTrade.getCounterParty()))
				validPartyAndCParty = 1;
		}

		if (td1 == null) {
			validTRN = 1;
		}
		
		if (newTrade.getEffectiveDate().compareTo(newTrade.getTradeDate()) >= 0) {
			if (newTrade.getMaturityDate().compareTo(newTrade.getEffectiveDate()) >= 0) {
				checkDate = 1;
			}
		}
		
		PartyValidationModel validBuyer = ValidationRepository.findByParty(newTrade.getBuyer());
		PartyValidationModel validSeller = ValidationRepository.findByParty(newTrade.getSeller());
		if (validBuyer != null && validSeller != null) {
			validBuyerSellerparty = 1;
		}

		if (!newTrade.getBuyer().equals(newTrade.getSeller())) {
			validBuyerSeller = 1;
		}
		
		if (validTRN == 1 && checkDate == 1 && validBuyerSeller == 1 && validPartyAndCParty == 1
				&& validBuyerSellerparty == 1) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<TradeDataModel> cancelTrade(String tradeRefNum) {
		// TODO Auto-generated method stub
		TradeDataModel getTrade=tradeRepository.findByTradeRefNum(tradeRefNum);
		if(getTrade!=null) {
			getTrade.setStatus("cancel");
			getTrade.setVersion(getTrade.getVersion()+1);
			return ResponseEntity.of(Optional.of(tradeRepository.save(getTrade)));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
}
