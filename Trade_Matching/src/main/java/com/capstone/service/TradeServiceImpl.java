package com.capstone.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.models.PartyValidationModel;
import com.capstone.models.TradeDataModel;
import com.capstone.repo.TradeRepo;
import com.capstone.repo.ValidationRepo;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepo tradeRepository;

	@Autowired
	private ValidationRepo ValidationRepository;

	@Override
	public List<TradeDataModel> getAllTrade() {
		// TODO Auto-generated method stub
		return tradeRepository.findAll();
	}

	public TradeDataModel insertTrade(TradeDataModel td) {
		// TODO Auto-generated method stub

		int checkDate = 0;
		int validTRN = 0;
		int validBuyerSeller = 0;
		int validPartyAndCParty = 0;
		int validBuyerSellerparty = 0;
		td.setTradeRefNum(td.getParty(), td.getTradeId());
		TradeDataModel td1 = tradeRepository.findByTradeRefNum(td.getTradeRefNum());
		td.setVersion(1);
		td.setStatus("Unconfirmed");
		// Insert new TimesStampp in Td at insertion

		// party validation
		PartyValidationModel pvm = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(td.getParty(),
				td.getPartyInstitution(), td.getPartyFullname());
		PartyValidationModel pvm1 = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(
				td.getCounterParty(), td.getCounterPartyInstitution(), td.getCounterPartyFullname());
		if (pvm != null && pvm1 != null) {
			if (!td.getParty().equals(td.getCounterParty()))
				validPartyAndCParty = 1;

		}

		if (td1 == null) {

			validTRN = 1;
		}
		if (td.getEffectiveDate().compareTo(td.getTradeDate()) >= 0) {
			if (td.getMaturityDate().compareTo(td.getEffectiveDate()) >= 0) {
				checkDate = 1;

			}
		}
		PartyValidationModel validBuyer = ValidationRepository.findByParty(td.getBuyer());
		PartyValidationModel validSeller = ValidationRepository.findByParty(td.getSeller());
		if (validBuyer != null && validSeller != null) {
			validBuyerSellerparty = 1;
		}

		if (!td.getBuyer().equals(td.getSeller())) {
			validBuyerSeller = 1;

		}

		if (validTRN == 1 && checkDate == 1 && validBuyerSeller == 1 && validPartyAndCParty == 1
				&& validBuyerSellerparty == 1) {
			return tradeRepository.save(td);
		}

		return null;

	}

	@Override
	public List<TradeDataModel> getTradeByPartyStatus(String party, String status) {
		List<TradeDataModel> allTrade=tradeRepository.findByPartyAndStatus(party, status);
		if(!allTrade.isEmpty()) {
			return allTrade;
		}
		return null;
	}

	@Override
	public TradeDataModel getTradeByTradeRefNum(String tradeRefNum) {
		TradeDataModel showTrade=tradeRepository.findByTradeRefNum(tradeRefNum);
		if(showTrade!=null) {
			return showTrade;
		}
		return null;
	}

	@Override
	public TradeDataModel updateTradeByTradeRefNum(String tradeRefNum, TradeDataModel newTradeData) {
		//Check if both have same TradeRefNum,and new td should be unconfirmed
		TradeDataModel existingTradeData= tradeRepository.findByTradeRefNum(tradeRefNum);
		if(existingTradeData!=null) {
			existingTradeData.setVersion(existingTradeData.getVersion()+1);
			// Insert new VersionTimesStampp  at insertion
			BeanUtils.copyProperties(newTradeData, existingTradeData);
			return tradeRepository.save(newTradeData);
		}
		return null;
		
	}

}
