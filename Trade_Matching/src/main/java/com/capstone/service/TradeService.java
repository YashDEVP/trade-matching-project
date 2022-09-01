package com.capstone.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.models.TradeDataModel;

//@Service
public interface TradeService {

	ResponseEntity<?> getAllTrade();

	ResponseEntity<?> insertTrade(TradeDataModel td);
	
	ResponseEntity<?> getTradeByPartyStatus(String party,String status);
	
	ResponseEntity<?> getTradeByTradeRefNum(String tradeRefNum);
	
	ResponseEntity<?> updateTradeByTradeRefNum(String tradeRefNum,TradeDataModel newTradeData);
	
	ResponseEntity<?> cancelTrade(String tradeRefNum);
	
	
}
