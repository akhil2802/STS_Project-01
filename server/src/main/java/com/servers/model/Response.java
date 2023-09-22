package com.servers.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	protected LocalDateTime timeStamp;
	protected int statusCode;
	private HttpStatus status;
	private String reason;
	protected String message;
	protected String developerMessage;
	protected Map<?, ?> data;
}
