package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id",nullable=false)
	private UserMaster user;
	@OneToOne
	@JoinColumn(name = "room_id", nullable = false) // Foreign key column in Booking table
	private Room room;

}
