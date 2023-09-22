package com.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
