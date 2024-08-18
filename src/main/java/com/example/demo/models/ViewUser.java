package com.example.demo.models;

import java.util.Set;

import com.example.demo.entities.Booking;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class ViewUser {


	    
	    private String username;
	    private String email;
	    private String role;
	    @JsonManagedReference
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<Booking> bookings;

}
