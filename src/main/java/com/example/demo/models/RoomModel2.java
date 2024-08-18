package com.example.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class RoomModel2 {
	
	private Integer roomNumber;
	@NotBlank(message = "Room type is mandatory")
	private String roomType;
	@NotNull(message = "Room price is mandatory")
	private Double roomPrice;
   @NotBlank
   @Pattern(regexp = "Available|Booked", message = "Room status must be either Available or Booked")
	private String roomstatus;


}
