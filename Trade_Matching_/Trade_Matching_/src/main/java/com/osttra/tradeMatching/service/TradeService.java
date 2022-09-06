package com.osttra.tradeMatching.service;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;

@Service
public interface TradeService {

	ResponseEntity<?> insertTrade(TradeDataInput td) throws NoTradeValidation;
	TradeData getTradeByTradeRefNum(String tradeRefNum) throws TradeNotFoundException;
	ResponseEntity<?> getAllTrade() throws TradeNotFoundException;
	List<TradeData> getTradesByParty(String party) throws TradeNotFoundException;


}
