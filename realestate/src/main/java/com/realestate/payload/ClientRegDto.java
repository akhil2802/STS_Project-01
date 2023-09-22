package com.realestate.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegDto {

    private Long clientId;
    private String clientName;
    private String clientAddress;
    private String phoneNumber;
    private String email;
    private String gender;
    private String username;
    private String password;
    
}
