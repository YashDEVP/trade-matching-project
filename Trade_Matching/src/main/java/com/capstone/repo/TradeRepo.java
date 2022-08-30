package com.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.models.TradeDataModel;

public interface TradeRepo extends JpaRepository<TradeDataModel, Long>{

	//@Query("SELECT t FROM TradeDataModel t WHERE t.tradeRefNum = ?1")
	TradeDataModel findByTradeRefNum(String tradeRefNum);
}