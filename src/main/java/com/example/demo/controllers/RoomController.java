package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Room;
import com.example.demo.models.RoomModel;
import com.example.demo.models.RoomModel2;
import com.example.demo.service.RoomService;

import jakarta.validation.Valid;

@RestController
public class RoomController {
	@Autowired
	private RoomService roomService;

	@GetMapping(value = "rooms")
	public ResponseEntity<List<RoomModel>> getAllRooms() {

		List<Room> allRooms = roomService.getAllRooms();

		ArrayList<RoomModel> roomList = new ArrayList<RoomModel>();

		allRooms.forEach(e -> {

			RoomModel roomModel = new RoomModel();

			BeanUtils.copyProperties(e, roomModel);

			roomList.add(roomModel);

		});

		return new ResponseEntity<>(roomList, HttpStatus.OK);

	}

	@GetMapping(value = "availableRooms")
	public ResponseEntity<List<RoomModel>> getAvailableRooms() {

		List<Room> allRooms = roomService.getAllRooms();

		List<Room> list = allRooms.stream().filter(e -> e.getRoomstatus().equalsIgnoreCase("available")).toList();

		ArrayList<RoomModel> roomList = new ArrayList<RoomModel>();

		list.forEach(e -> {

			RoomModel roomModel = new RoomModel();

			BeanUtils.copyProperties(e, roomModel);

			roomList.add(roomModel);

		});

		return new ResponseEntity<>(roomList, HttpStatus.OK);

	}

	@GetMapping("room/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable("id") Integer id) {

		Room room = roomService.findById(id).orElse(null);
		if (room == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(room, HttpStatus.OK);

	}

	@PostMapping("room")
	public ResponseEntity<String> addRoom(@Valid @RequestBody RoomModel2 model) {

		Room roomNo = roomService.findByRoomNo(model.getRoomNumber());

		if (roomNo != null) {

			return new ResponseEntity<>(" Room with that number already exists", HttpStatus.BAD_REQUEST);
		}
		
		Room room = new Room();
		BeanUtils.copyProperties(model, room);
			

		room.setRoomNumber(model.getRoomNumber());

//		room.setRoomstatus("available");

		roomService.saveRoom(room);

		return new ResponseEntity<>(" New Room Created", HttpStatus.CREATED);
	}

	@PutMapping("room")
	public ResponseEntity<String> updateRoom(@RequestBody RoomModel model) {

		Room findByRoomNo = roomService.findByRoomNo(model.getRoomNumber());
		Optional<Room> findById = roomService.findById(model.getRoomId());

		if (!findById.isPresent()) {
			return new ResponseEntity<>("Room Id does not exist", HttpStatus.BAD_REQUEST);
		}
		if (findByRoomNo != null) {
			return new ResponseEntity<>("RoomNo already exist", HttpStatus.BAD_REQUEST);
		}
		Room room = findById.get();
		BeanUtils.copyProperties(model, room);

		roomService.saveRoom(room);

		return new ResponseEntity<>("Room Updated", HttpStatus.CREATED);
	}

	@DeleteMapping("room/{id}")
	public ResponseEntity<String> deleteRoom(@PathVariable("id")Integer id) {

		roomService.deleteRoomById(id);

		return new ResponseEntity<>("Room Deleted", HttpStatus.NO_CONTENT);
	}

}
