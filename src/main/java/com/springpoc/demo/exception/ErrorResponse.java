package com.springpoc.demo.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorResponse {

	private String code;
	private String message;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	public ErrorResponse() {
		timestamp = LocalDateTime.now();
	}
	public ErrorResponse(String code, String message) {
		this();
		this.code = code;
		this.message = message;
	}
	
}
