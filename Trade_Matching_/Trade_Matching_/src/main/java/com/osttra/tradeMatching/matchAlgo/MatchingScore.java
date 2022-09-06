package com.osttra.tradeMatching.matchAlgo;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.service.MatchingService;

public class MatchingScore {

	@Autowired
    MatchingService matchService;



   public int calculateMatchingScore(TradeData tradeA, TradeData cpTrade) {
        HashMap<String, Integer> scores = new HashMap<String, Integer>();
        
        
        int tradeDatePercent = 10;
        int effDatePercent = 10;
        int instumentIdPercent = 18;
        int notionalAmountPercent = 10;
        int maturitydatePercent = 8;
        int currencyPercent = 8;
        int sellerPercent = 18;
        int buyerPercent = 18;




       int percent = 0;
        String oppositePartyRef = cpTrade.getTradeRefNum();



       System.out.println(tradeA.getTradeRefNum() + " trade matching score calculation with "
                + cpTrade.getTradeRefNum() + "------------------------------------");



       if (tradeA.getSeller().equals(cpTrade.getSeller())) {
            percent += sellerPercent;
            System.out.println("1-Seller Matched");
        }
        if (tradeA.getBuyer().equals(cpTrade.getBuyer())) {
            percent += buyerPercent;
            System.out.println("2-Buyer Matched");
        }
        if (tradeA.getTradeDate().equals(cpTrade.getTradeDate())) {
            percent += tradeDatePercent;
            System.out.println("3-TradeDate Matched");
        }
        if (tradeA.getEffectiveDate().equals(cpTrade.getEffectiveDate())) {
            percent += effDatePercent;
            System.out.println("4-Effective data Matched");
        }
        if (tradeA.getInstrumentId().equals(cpTrade.getInstrumentId())) {
            percent += instumentIdPercent;
            System.out.println("5-Instrument Id Matched");
        }
        if (tradeA.getNotionalAmount() == cpTrade.getNotionalAmount()) {
            percent += notionalAmountPercent;
            System.out.println("6-Notional Amount Matched");
        }
        if (tradeA.getMaturityDate().equals(cpTrade.getMaturityDate())) {
            percent += maturitydatePercent;
            System.out.println("7-MaturityDate Matched");
        }
        if (tradeA.getCurrency().equals(cpTrade.getCurrency())) {
            percent += currencyPercent;
            System.out.println("8-Currency Matched");
        }



       System.out.println("------------------------------------------------");
        scores.put(oppositePartyRef, percent);

       return percent;
    }

}
