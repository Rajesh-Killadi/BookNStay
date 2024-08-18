package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.Room;
import com.example.demo.entities.UserMaster;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class HotelBookingApiApplication {
	@Autowired
	private RoomRepository roomRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	/*
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 */
	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApiApplication.class, args);
	}

	@PostConstruct
	public void uploadRooms() {

		System.out.println("PostConstruct Method is executing.........");

				List<Room> rooms = Arrays.asList(
					    new Room(1, 101, "Double", 1000.0, "Available", null),
					    new Room(2, 102, "Single", 500.0, "Available", null),
					    new Room(3, 103, "Double", 1000.0, "Available", null),
					    new Room(4, 104, "Single", 500.0, "Available", null),
					    new Room(5, 105, "Double", 1000.0, "Available", null),
					    new Room(6, 106, "Single", 500.0, "Available", null),
					    new Room(7, 107, "Double", 1000.0, "Available", null),
					    new Room(8, 108, "Single", 500.0, "Available", null),
					    new Room(9, 109, "Double", 1000.0, "Available", null),
					    new Room(10, 110, "Single", 500.0, "Available", null),
					    new Room(11, 111, "Double", 1000.0, "Available", null),
					    new Room(12, 112, "Single", 500.0, "Available", null),
					    new Room(13, 113, "Double", 1000.0, "Available", null),
					    new Room(14, 114, "Single", 500.0, "Available", null),
					    new Room(15, 115, "Double", 1000.0, "Available", null),
					    new Room(16, 116, "Single", 500.0, "Available", null),
					    new Room(17, 117, "Double", 1000.0, "Booked", null),
					    new Room(18, 118, "Single", 500.0, "Available", null),
					    new Room(19, 119, "Double", 1000.0, "Available", null),
					    new Room(20, 120, "Single", 500.0, "Booked", null),
					    new Room(21, 121, "Double", 1000.0, "Available", null),
					    new Room(22, 122, "Single", 500.0, "Available", null),
					    new Room(23, 123, "Double", 1000.0, "Available", null),
					    new Room(24, 124, "Single", 500.0, "Available", null),
					    new Room(25, 125, "Double", 1000.0, "Available", null),
					    new Room(26, 126, "Single", 500.0, "Available", null),
					    new Room(27, 127, "Double", 1000.0, "Available", null),
					    new Room(28, 128, "Single", 500.0, "Available", null),
					    new Room(29, 129, "Double", 1000.0, "Available", null),
					    new Room(30, 130, "Single", 500.0, "Available", null),
					    new Room(31, 131, "Double", 1000.0, "Available", null),
					    new Room(32, 132, "Single", 500.0, "Available", null),
					    new Room(33, 133, "Double", 1000.0, "Available", null),
					    new Room(34, 134, "Single", 500.0, "Available", null),
					    new Room(35, 135, "Double", 1000.0, "Available", null),
					    new Room(36, 136, "Single", 500.0, "Available", null),
					    new Room(37, 137, "Double", 1000.0, "Available", null),
					    new Room(38, 138, "Single", 500.0, "Available", null),
					    new Room(39, 139, "Double", 1000.0, "Available", null),
					    new Room(40, 140, "Single", 500.0, "Available", null),
					    new Room(41, 141, "Double", 1000.0, "Available", null),
					    new Room(42, 142, "Single", 500.0, "Available", null),
					    new Room(43, 143, "Double", 1000.0, "Available", null),
					    new Room(44, 144, "Single", 500.0, "Available", null),
					    new Room(45, 145, "Double", 1000.0, "Available", null),
					    new Room(46, 146, "Single", 500.0, "Available", null),
					    new Room(47, 147, "Double", 1000.0, "Available", null),
					    new Room(48, 148, "Single", 500.0, "Available", null),
					    new Room(49, 149, "Double", 1000.0, "Available", null),
					    new Room(50, 150, "Single", 500.0, "Available", null)
					);
		roomRepo.saveAll(rooms);

		List<UserMaster> users = Arrays.asList(
				new UserMaster(1, "luffy", "piratekingluffy@gmail.com",encoder.encode("monkey.d.luffy") , "ROLE_ADMIN",1, null),
				new UserMaster(2, "zoro", "zorolostagain@gmail.com", encoder.encode("roronoazoro"), "ROLE_USER",1, null));

		userRepo.saveAll(users);
	}
//
//	@PostConstruct
//	public void init() {
//		 jdbcTemplate.execute("ALTER SEQUENCE ROOM_ROOMID_SEQ RESTART WITH 101");
//	}

}
