package com.realestate.mapper;

import com.realestate.entities.ClientReg;
import com.realestate.entities.Sale;
import com.realestate.payload.SaleDto;

public class SaleMapper {

	// map Sale entity to SaleDto:
	public static SaleDto mapToSaleDto(Sale sale, ClientReg clientReg) {

		return SaleDto.builder().clientId(sale.getClientId()).agentId(sale.getAgentId())
				.propertyId(sale.getPropertyId()).saleDate(sale.getSaleDate())
				.clientReg(ClientRegMapper.mapToClientRegDto(clientReg)).build();
	}

	// map Sale entity to SaleDto:
	public static Sale mapToSale(SaleDto saleDto) {

		return Sale.builder().clientId(saleDto.getClientId()).agentId(saleDto.getAgentId())
				.propertyId(saleDto.getPropertyId()).saleDate(saleDto.getSaleDate()).build();
	}
}
