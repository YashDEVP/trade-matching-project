package com.osttra.tradeMatching.constant;

public class Constants {

	//check TradeValidationImpl class
	public static final String MESSAGE1 = "valid";
	public static final String ERROR ="error";
	public static final String ERROR1 = "Please enter the valid party details or counter party details";
	public static final String ERROR2 = "Already Exists tradeRefNum in database please enter valid TradeRefNum";
	public static final String ERROR3 = "Please check Effective Date or Trade date or Maturity date";
	public static final String ERROR4 = "Please check buyer or seller details";
	public static final String ERROR5 = "Buyer and Seller are same please correct it";
	public static final String ERROR6 = "Invalid TradeRefNumber Please enter valid TradeRefNumber";
	public static final String ERROR7="No Trade Found!, Please insert any data in database";
	
	
	//Check TradeServiceImpl class
	public static final String noTradeReferenceNumber ="Trade is not found with Trade Reference Number:";
	public static final String notValidateTrade ="Trade is not validated: ";
	public static final String noPartiesExist ="No parties exist with: ";
	
	//check TradeData class
	public static final String tableNameTradeData="trade_data";
	public static final String partyMessage="party shouldn't be empty.";
	public static final String tradeIdMessage ="Trade id shouldn't be empty.";
	public static final String partyInstitutionMessage="Party Institution shouldn't be empty.";
	public static final String counterPartyMessage="Counter Party shouldn't be empty.";
	public static final String CouterPartyInstitutionMessage="Counter Party Institution shouldn't be empty.";
	public static final String partyFullNameMessage="Party Full Name shouldn't be empty.";
	public static final String counterPartyFullNameMessage="Counter Party Full Name shouldn't be empty.";
	public static final String instrumentIdMessage="Instrument ID can't be empty.";
	public static final String notionalAmountMessage="Notational Amount can't be empty.";
	public static final String currencyMessage="Currency shouldn't be empty. Example: INR";
	public static final String sellerMessage= "Seller can't be empty.";
	public static final String buyerMessage ="Buyer Can't be empty.";
	public static final String columnDefinitionVersion="INT DEFAULT '1'";
	public static final String columnDefinitionStatus="VARCHAR(40) DEFAULT 'Unconfirmed'";
	public static final String statusExample="unconfirmed";
	public static final String versionExample="1";
	
	//check PartyValidation class
	public static final String tableNamePartyValidation="party_info";
	public static final String condPartyFullNameMessage="Party Full Name should be more than 2 and less than 150 characters";

	//check TradeController class
	public static final String getAllTradesMessage="View list of all available Trades.";
	public static final String getAllPartiesThroughParty="Get All Trades Using Party";
}
