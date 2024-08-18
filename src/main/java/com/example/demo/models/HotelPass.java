package com.example.demo.models;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HotelPass {
	
	private Integer bookingId;
	
	private String username;
	
    private String email;

	private String roomNumber;
	
	private String bookingStatus;
	
	
}
