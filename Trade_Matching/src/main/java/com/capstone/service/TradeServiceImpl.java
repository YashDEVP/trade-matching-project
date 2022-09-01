package com.capstone.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.models.MatchingScoreModel;
import com.capstone.models.PartyValidationModel;
import com.capstone.models.TradeDataModel;
import com.capstone.models.weightageModel;
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
	private MatchingRepo MatchingRepository;
	
	
	@Override
	public ResponseEntity<?> getAllTrade() {
		// TODO Auto-generated method stub
		List<TradeDataModel> allTrade=tradeRepository.findAll();
		if(allTrade.isEmpty()) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return new ResponseEntity<>("Please insert any data in database", HttpStatus.NOT_FOUND);
		}
		 return ResponseEntity.of(Optional.of(allTrade));
	}

	public ResponseEntity<?> insertTrade(TradeDataModel newTrade) {
		
		String isValid=checkValidTrade(newTrade);
		if(isValid.equals("Valid")) {
			List<TradeDataModel> matchedTrade=tradeRepository.findByPartyAndCounterPartyAndStatus(
					newTrade.getCounterParty(), 
					newTrade.getParty(),
					newTrade.getStatus());
			
			System.out.println(matchedTrade.size());
			TradeDataModel newSavedTrade=tradeRepository.save(newTrade);
			TradeDataModel getSavedTrade=tradeRepository.findByTradeRefNum(newTrade.getTradeRefNum());
			calculateMatchingScore(getSavedTrade,matchedTrade);
			return ResponseEntity.of(Optional.of(newSavedTrade));
		}
		return new ResponseEntity<>(isValid, HttpStatus.NOT_FOUND);
	}

	
	@Override
	public ResponseEntity<?> getTradeByPartyStatus(String party, String status) {
		
		List<TradeDataModel> allTrade=tradeRepository.findByPartyAndStatus(party, status);
		if(allTrade.isEmpty()) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return new ResponseEntity<>("Invalid party or status ", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.of(Optional.of(allTrade));
	}

	
	@Override
	public ResponseEntity<?> getTradeByTradeRefNum(String tradeRefNum) {
		TradeDataModel showTrade=tradeRepository.findByTradeRefNum(tradeRefNum);
		if(showTrade!=null) {
			return ResponseEntity.of(Optional.of(showTrade)); 
			//return new ResponseEntity<>("Invalid TradeRefNumber Please enter valid TradeRefNumber ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Invalid TradeRefNumber Please enter valid TradeRefNumber ", HttpStatus.NOT_FOUND);
	}

	
	@Override
	public ResponseEntity<?> updateTradeByTradeRefNum(String tradeRefNum, TradeDataModel newTradeData) {
		//Check if both have same TradeRefNum,and new td should be unconfirmed
		TradeDataModel existingTradeData= tradeRepository.findByTradeRefNum(tradeRefNum);
		if(existingTradeData!=null) {
			// Insert new VersionTimesStampp  at insertion
			BeanUtils.copyProperties(newTradeData, existingTradeData);
			newTradeData.setVersion(existingTradeData.getVersion()+1);
			return ResponseEntity.of(Optional.of(tradeRepository.save(newTradeData)));
			//return new ResponseEntity<>("Invalid TradeRefNumber please enter valid TradeRefNum", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Invalid TradeRefNumber please enter valid TradeRefNum", HttpStatus.NOT_FOUND);	
}
	
	public void calculateMatchingScore(TradeDataModel newTrade,List<TradeDataModel>matchedTrade) {
		
		int calculateWeight=0;
//		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		Date formatTradeDate=dateFormat.parse(newTrade.getTradeDate());
				
		weightageModel weight=new weightageModel();
		
		for(TradeDataModel td: matchedTrade) {
			MatchingScoreModel matchingScore=new MatchingScoreModel();
			if(newTrade.getTradeDate().compareTo(td.getTradeDate())==0) {
				System.out.println("Tradedate");
				calculateWeight+=weight.getTradeDatePercent();
			}else {
				System.out.println(newTrade.getTradeDate());
				System.out.println(td.getTradeDate());
				System.err.println("data if not running ");
			}
			if(newTrade.getEffectiveDate().equals(td.getEffectiveDate())) {
				System.out.println("Effectivedate");
				calculateWeight+=weight.getEffectiveDatePercent();
			}
			if(newTrade.getMaturityDate().equals(td.getMaturityDate())) {
				System.out.println("mdate");
				calculateWeight+=weight.getMaturityDatePercent();
			}
			if(newTrade.getInstrumentId().equals(td.getInstrumentId())){
				System.out.println("instrument");
				calculateWeight+=weight.getInstrumentIdPercent();
			}
			if(newTrade.getNotionalAmount()==td.getNotionalAmount()) {
				System.out.println("nAmount");
				calculateWeight+=weight.getNotionalAmountPercent();
			}
			if(newTrade.getCurrency().equals(td.getCurrency())) {
				System.out.println("currency");
				calculateWeight+=weight.getCurrencyPercent();
			}
			if(newTrade.getSeller().equals(td.getSeller())) {
				System.out.println("sell");
				calculateWeight+=weight.getSellerPercent();
			}
			if(newTrade.getBuyer().equals(td.getBuyer())) {
				System.out.println("buy");
				calculateWeight+=weight.getBuyerPercent();
			}
			
			matchingScore.setPartyTradeRefNum(newTrade.getTradeRefNum());
			matchingScore.setCounterPartyTradeRefNum(td.getTradeRefNum());
			matchingScore.setMatchingPercent(calculateWeight);
			matchingScore.setsNo((long) 1);
			if(calculateWeight==100) {
				matchingScore.setStatus("confirmed");
				
				//break;
			}else {
				matchingScore.setStatus("Unconfirmed");
			}
			MatchingRepository.save(matchingScore);
			calculateWeight=0;
		}
	}

	
	public String checkValidTrade(TradeDataModel newTrade) {
		int checkDate = 0;
		int validTRN = 0;
		int validBuyerSeller = 0;
		int validPartyAndCParty = 0;
		int validBuyerSellerparty = 0;
		String returnMsg="Error";
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
		}else {
			returnMsg="Please enter the valid party details or counter party details";
		}

		if (td1 == null) {
			validTRN = 1;
		}else {
			returnMsg="Already Exists tradeRefNum in database please enter valid TradeRefNum";
		}
		
		if (newTrade.getEffectiveDate().compareTo(newTrade.getTradeDate()) >= 0) {
			if (newTrade.getMaturityDate().compareTo(newTrade.getEffectiveDate()) >= 0) {
				checkDate = 1;
			}
		}else {
			returnMsg="Please check Effective Date or Trade date or Maturity date";
		}
		
		PartyValidationModel validBuyer = ValidationRepository.findByParty(newTrade.getBuyer());
		PartyValidationModel validSeller = ValidationRepository.findByParty(newTrade.getSeller());
		if (validBuyer != null && validSeller != null) {
			validBuyerSellerparty = 1;
		}else {
			returnMsg="please check buyer or seller details";
		}

		if (!newTrade.getBuyer().equals(newTrade.getSeller())) {
			validBuyerSeller = 1;
		}else {
			returnMsg="Buyer or Seller are same please correct it";
		}
		
		if (validTRN == 1 && checkDate == 1 && validBuyerSeller == 1 && validPartyAndCParty == 1
				&& validBuyerSellerparty == 1) {
			return "Valid";
		}
		return returnMsg;
	}

	@Override
	public ResponseEntity<?> cancelTrade(String tradeRefNum) {
		// TODO Auto-generated method stub
		TradeDataModel getTrade=tradeRepository.findByTradeRefNum(tradeRefNum);
		if(getTrade!=null) {
			getTrade.setStatus("cancel");
			getTrade.setVersion(getTrade.getVersion()+1);
			return ResponseEntity.of(Optional.of(tradeRepository.save(getTrade)));
			//return new ResponseEntity<>("Please insert any data in database", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("invalid ,Please insert a valid TradeRefNumber", HttpStatus.NOT_FOUND);	
	}
	

}
