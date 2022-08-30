package com.capstone.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.models.TradeDataModel;

public interface TradeRepo extends JpaRepository<TradeDataModel, Long>{

	TradeDataModel findByTradeRefNum(String tradeRefNum);
	List<TradeDataModel> findByPartyAndStatus(String party,String status);
}