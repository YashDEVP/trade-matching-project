package com.capstone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.models.TradeDataModel;
import com.capstone.service.TradeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/")
@Api(value="Trade Controller")
public class TradeController {

	@Autowired
	private TradeService tradeService;
	
	//trims the length of empty string
//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder) {
//	StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//	dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//	}

	@ApiOperation(value="View a list of available Trades.")
    @ApiResponses(value = {
            @ApiResponse(code= 200 , message= "Successfull Retrived Trade List"),
            @ApiResponse(code= 401 ,  message="You are not authorized to view the resource"),
            @ApiResponse(code= 403, message =  "Accessing the resource you where trying to reach is forbidden")
    })
	
	
	@GetMapping("/trade/")
	public List<TradeDataModel> getAllTrade(){
		return tradeService.getAllTrade();
	}
	
	@ApiOperation(value="Inserting trade in DB after Validating.")
	@PostMapping("/trade/")
	public ResponseEntity<TradeDataModel> insertTrade(@RequestBody TradeDataModel td){
		TradeDataModel showTrade= tradeService.insertTrade(td);
		if(showTrade==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.of(Optional.of(showTrade));
	}
	
	
	@ApiOperation(value="Search a trade by the help of party and status.")
	@GetMapping("/trade/{party}/{status}")
	public ResponseEntity<List<TradeDataModel>> getTradeByPartyStatus(String party,String status ){
		List<TradeDataModel> allTrade= tradeService.getTradeByPartyStatus(party, status);
		if(allTrade==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(allTrade));
	}
	
	@ApiOperation(value="View a particular Trade by TradeRefNumber.")
	@GetMapping("/trade/{tradeRefNum}")
	public ResponseEntity<TradeDataModel> getTradeByTradeRefNum(String tradeRefNum ){
		TradeDataModel allTrade= tradeService.getTradeByTradeRefNum(tradeRefNum);
		if(allTrade==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(allTrade));
	}
	
	@ApiOperation(value="Update a particular Trade by TradeRefNumber.")
	@PutMapping("/trade/{tradeRefNum}")
	public ResponseEntity<TradeDataModel> updateTradeByTradeRefNum(@PathVariable String tradeRefNum, @RequestBody TradeDataModel newTradeData){
		TradeDataModel allTrade= tradeService.updateTradeByTradeRefNum( tradeRefNum, newTradeData);
		if(allTrade==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(allTrade));
	}
	
	
	
}
