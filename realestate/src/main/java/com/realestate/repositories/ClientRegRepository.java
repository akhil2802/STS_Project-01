package com.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.entities.ClientReg;

public interface ClientRegRepository extends JpaRepository<ClientReg, Long> {

}
