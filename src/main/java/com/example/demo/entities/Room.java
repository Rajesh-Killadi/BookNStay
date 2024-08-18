package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomId;

	private Integer roomNumber;
	private String roomType;
	private Double roomPrice;
	private String roomstatus;
	@JsonIgnore
	@OneToOne(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private Booking booking;
}
