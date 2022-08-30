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
import org.springframework.web.bind.annotation.PostMapping;
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

	@ApiOperation(value="View a list of available Trades")
    @ApiResponses(value = {
            @ApiResponse(code= 200 , message= "Successfull Retrived Trade List"),
            @ApiResponse(code= 401 ,  message="You are not authorized to view the resource"),
            @ApiResponse(code= 403, message =  "Accessing the resource you where trying to reach is forbidden")
    })
	
	
	@GetMapping("/trade/")
	public List<TradeDataModel> getAllTrade(){
		return tradeService.getAllTrade();
	}
	
	@PostMapping("/trade/")
	public ResponseEntity<TradeDataModel> saveTrade(@RequestBody TradeDataModel td){
		TradeDataModel showTrade= tradeService.saveTrade(td);
		if(showTrade==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.of(Optional.of(showTrade));
	}
}
