package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Booking;
import com.example.demo.entities.UserMaster;
import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository Brepo;
	@Autowired
	private UserRepository Urepo;

	public Booking getBookingById(Integer id) {

		return Brepo.findById(id).orElse(null);

	}

	public List<Booking> getBookingByUserMail(String mail) {

		Optional<UserMaster> findByEmail = Urepo.findByEmail(mail);
		UserMaster userMaster = findByEmail.get();
		return Brepo.findByUser(userMaster);

	}

	public List<Booking> getAllBookings() {

		return Brepo.findAll();
	}

	public Booking saveBooking(Booking booking) {

		return Brepo.save(booking);

	}
	
	public boolean deleteBooking(Integer id) {
		
		Brepo.deleteById(id);
		
		return true;
	}

}
