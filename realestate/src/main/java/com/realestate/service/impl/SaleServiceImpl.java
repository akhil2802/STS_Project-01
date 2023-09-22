package com.realestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.entities.ClientReg;
import com.realestate.entities.Sale;
import com.realestate.mapper.SaleMapper;
import com.realestate.payload.SaleDto;
import com.realestate.repositories.ClientRegRepository;
import com.realestate.repositories.SaleRepository;
import com.realestate.service.SaleService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SaleServiceImpl implements SaleService {

	
	private final ClientRegRepository clientRegRepo;
	
	private final SaleRepository saleRepo;
	
	@Autowired
	public SaleServiceImpl(ClientRegRepository clientRegRepo, SaleRepository saleRepo) {
		
		this.clientRegRepo = clientRegRepo;
		this.saleRepo = saleRepo;
	}

	@Override
	public SaleDto saveSale(SaleDto saleDto) {

		ClientReg clientReg = clientRegRepo.findById(saleDto.getClientId())
				.orElseThrow(() -> new EntityNotFoundException("Client not found with id:" + saleDto.getClientId()));
		
		Sale sale = SaleMapper.mapToSale(saleDto);
		Sale savedSale = this.saleRepo.save(sale);
		
		return SaleMapper.mapToSaleDto(savedSale, clientReg);
	}

}
