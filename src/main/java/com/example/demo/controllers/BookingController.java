package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Booking;
import com.example.demo.entities.Room;
import com.example.demo.entities.UserMaster;
import com.example.demo.models.HotelPass;
import com.example.demo.service.BookingService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;

@RestController
public class BookingController {
	@Autowired
	private BookingService bookingService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoomService roomService;
	
	@GetMapping("bookings")
	public ResponseEntity<List<Booking>> getAllBookings(){
		
		List<Booking> allBookings = bookingService.getAllBookings();
		
		return new ResponseEntity<>(allBookings,HttpStatus.OK);
	}
	
	@GetMapping("booking/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("id") Integer id){
		
		 Booking bookingById = bookingService.getBookingById(id);
		return new ResponseEntity<>(bookingById,HttpStatus.OK);
		
	}
	
	@DeleteMapping("booking/{id}")
	public ResponseEntity<String> deleteBookingById(@PathVariable("id") Integer id){
		
		  Booking booking = bookingService.getBookingById(id);
		  
		  Room room = booking.getRoom();
		  
		  room.setRoomstatus("Available");
		  roomService.saveRoom(room);
		
		  boolean deleteBooking = bookingService.deleteBooking(id);
		  
		  
		return new ResponseEntity<>("Booking Deleted",HttpStatus.NO_CONTENT);
		
	}
	

	/*
	 * @GetMapping("myBookings") public ResponseEntity<List<Booking>>
	 * myBookings(@RequestParam("username")String mail){
	 * 
	 * UserMaster user = userService.findByEmail(mail);
	 * 
	 * if(user == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
	 * 
	 * List<Booking> bookingByUserMail = bookingService.getBookingByUserMail(mail);
	 * 
	 * return new ResponseEntity<>(bookingByUserMail,HttpStatus.OK); }
	 */
	
	@PostMapping("booking")
	public ResponseEntity<HotelPass> createBooking(@RequestParam("roomId") Integer roomid, @RequestParam("email")String email){

		UserMaster user = userService.findByEmail(email);
		
		Optional<Room> roomId = roomService.findById(roomid);
		Room room = roomId.get();
		 
		
		
		if(roomId != null && room.getRoomstatus().equalsIgnoreCase("available")&& user != null) {
			
			Booking booking = new Booking();
			
			room.setRoomstatus("Booked");
			
			booking.setRoom(room);
			booking.setUser(user);
			
			roomService.saveRoom(room);
			
			Booking saveBooking = bookingService.saveBooking(booking);
			
			HotelPass pass = new HotelPass();
			pass.setBookingStatus("Booked");
			pass.setEmail(user.getEmail());
			pass.setRoomNumber("Room-"+roomId.get().getRoomNumber());
			pass.setUsername(user.getUsername());
			pass.setBookingId(saveBooking.getId());
			
			return new ResponseEntity<>(pass,HttpStatus.CREATED);
			
			
			
		}
		
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		
	}
	
		
		
	
	

}
