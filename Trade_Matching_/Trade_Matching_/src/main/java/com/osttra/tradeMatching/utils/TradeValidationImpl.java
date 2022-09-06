package com.osttra.tradeMatching.utils;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.models.PartyValidation;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.repo.ValidationRepo;


@Service
public class TradeValidationImpl implements TradeValidation {

	@Autowired
	private TradeRepo tradeRepository;

	@Autowired
	private ValidationRepo ValidationRepository; 
	
	
	
	@Override
	public String checkValidTrade(TradeData newTrade) {
		
		System.out.println("check valid tarde????");
		int checkDate = 0;
		int validTRN = 0;
		int validBuyerSeller = 0;
		int validPartyAndCParty = 0;
		int validBuyerSellerparty = 0;

		String returnMsg =Constants.ERROR;

		int validInstitution=0;
		int validPartyFullName=0;
		int validTradeDate=0;
		
		//set TradeRefNum

		newTrade.setTradeRefNum(newTrade.getParty(), newTrade.getTradeId());
		newTrade.setId(0L);
		TradeData td1 = tradeRepository.findByTradeRefNum(newTrade.getTradeRefNum());
		
		newTrade.setVersion(1);
		newTrade.setStatus(Constants.statusExample);
		// Insert new TimesStampp in Td at insertion

		// party validation
		PartyValidation pvm = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(newTrade.getParty(),
				newTrade.getPartyInstitution(), newTrade.getPartyFullname());
		PartyValidation pvm1 = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(
				newTrade.getCounterParty(), newTrade.getCounterPartyInstitution(), newTrade.getCounterPartyFullname());
		
		if (pvm != null && pvm1 != null) {
			if (!newTrade.getParty().equals(newTrade.getCounterParty()))
				validPartyAndCParty = 1;
		}else {
			returnMsg = Constants.ERROR1;
		}

		if (td1 == null) {
			validTRN = 1;
		}else {
			returnMsg = Constants.ERROR2;
		}
		
		if (newTrade.getEffectiveDate().compareTo(newTrade.getTradeDate()) >= 0) {
			if (newTrade.getMaturityDate().compareTo(newTrade.getEffectiveDate()) >= 0) {
				checkDate = 1;
			}
		}else {
			returnMsg = Constants.ERROR3;
		}
		
		PartyValidation validBuyer = ValidationRepository.findByParty(newTrade.getBuyer());
		PartyValidation validSeller = ValidationRepository.findByParty(newTrade.getSeller());
		if (validBuyer != null && validSeller != null) {
			validBuyerSellerparty = 1;
		}else {
			returnMsg = Constants.ERROR4;
		}

		if (!newTrade.getBuyer().equals(newTrade.getSeller())) {
			validBuyerSeller = 1;
		}else {
			returnMsg = Constants.ERROR5;
		}
		if(!newTrade.getPartyInstitution().equals(newTrade.getCounterPartyInstitution())) {
			validInstitution=1;
		}
		if(!newTrade.getPartyFullname().equals(newTrade.getCounterPartyFullname())) {
			validPartyFullName=1;
		}
		if(newTrade.getTradeDate().compareTo(LocalDate.now())>=0) {
			System.out.println(newTrade.getTradeDate());
			System.out.println(LocalDate.now());
			validTradeDate = 1;
		}
		if (validTRN == 1 && checkDate == 1 && validBuyerSeller == 1 && validPartyAndCParty == 1
				&& validBuyerSellerparty == 1 && validInstitution==1 && validPartyFullName==1 && validTradeDate==1) {
			return Constants.MESSAGE1;
		}
		
		return returnMsg;
	}

}
