package com.osttra.tradeMatching.utils;

import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.models.TradeData;

public interface TradeValidation {

	String checkValidTrade(TradeData newTrade);
}