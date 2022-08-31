package com.capstone.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.models.TradeDataModel;

//@Service
public interface TradeService {

	ResponseEntity<List<TradeDataModel>> getAllTrade();

	ResponseEntity<TradeDataModel> insertTrade(TradeDataModel td);
	
	ResponseEntity<List<TradeDataModel>> getTradeByPartyStatus(String party,String status);
	
	ResponseEntity<TradeDataModel> getTradeByTradeRefNum(String tradeRefNum);
	
	ResponseEntity<TradeDataModel> updateTradeByTradeRefNum(String tradeRefNum,TradeDataModel newTradeData);
	
	ResponseEntity<TradeDataModel> cancelTrade(String tradeRefNum);
	
	
}
