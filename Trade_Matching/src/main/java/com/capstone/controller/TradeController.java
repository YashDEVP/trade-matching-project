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
import org.springframework.web.bind.annotation.PatchMapping;
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


	@ApiOperation(value="View a list of available Trades.")
    @ApiResponses(value = {
            @ApiResponse(code= 200 , message= "Successfull Retrived Trade List"),
            @ApiResponse(code= 401 ,  message="You are not authorized to view the resource"),
            @ApiResponse(code= 403, message =  "Accessing the resource you where trying to reach is forbidden")
    })
	
	
	@GetMapping("/trade/")
	public ResponseEntity<?> getAllTrade(){
		
		return tradeService.getAllTrade();
	}
	
	@ApiOperation(value="Inserting trade in DB after Validating.")
	@PostMapping("/trade/")
	public ResponseEntity<?> insertTrade(@RequestBody TradeDataModel td){
		return tradeService.insertTrade(td);
	}
	
	
	@ApiOperation(value="Search a trade by the help of party and status.")
	@GetMapping("/trade/{party}/{status}")
	public ResponseEntity<?> getTradeByPartyStatus(String party,String status ){
		return tradeService.getTradeByPartyStatus(party, status);
	}
	
	
	@ApiOperation(value="View a particular Trade by TradeRefNumber.")
	@GetMapping("/trade/{tradeRefNum}")
	public ResponseEntity<?> getTradeByTradeRefNum(String tradeRefNum ){
		return tradeService.getTradeByTradeRefNum(tradeRefNum);
	}
	
	@ApiOperation(value="Update a particular Trade by TradeRefNumber.")
	@PutMapping("/trade/{tradeRefNum}")
	public ResponseEntity<?> updateTradeByTradeRefNum(@PathVariable String tradeRefNum, @RequestBody TradeDataModel newTradeData){
		return tradeService.updateTradeByTradeRefNum( tradeRefNum, newTradeData);
		
	}
	
	@ApiOperation(value="Update a particular Trade by updating status")
	@PatchMapping("/trade/cancelTrade")
	public ResponseEntity<?> cancelTrade(String tradeRefNum ){
		return tradeService.cancelTrade(tradeRefNum);
	}
	
}
