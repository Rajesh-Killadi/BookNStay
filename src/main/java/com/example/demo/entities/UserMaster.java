package com.example.demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMaster {
	   	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    
	    private String username;
	    private String email;
	    private String password;
	    private String role;
	    private Integer enabled;
	    @JsonManagedReference
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Booking> bookings;

	    
	    

}
