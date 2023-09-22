package com.realestate.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaleDto {
	
	 private Long clientId;
	 
	 private Long agentId;
	 
	 private Long propertyId;
	 
	 @JsonFormat(pattern = "yyyy-MM-dd")
	 private Date saleDate;

	 private ClientRegDto clientReg;
}
