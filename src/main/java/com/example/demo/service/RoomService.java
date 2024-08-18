package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Room;
import com.example.demo.repositories.RoomRepository;

@Service
public class RoomService {
	@Autowired
	private RoomRepository roomRepo;

	public List<Room> getAllRooms() {

		return roomRepo.findAll();
	}

	public boolean saveRoom(Room room) {

		roomRepo.save(room);
		return true;
	}

	public boolean deleteRoomById(Integer id) {

		roomRepo.deleteById(id);
		return true;
	}

	public Optional<Room> findById(Integer id) {

		Optional<Room> findById = roomRepo.findById(id);

		return findById;
	}

	public Room findByRoomNo(Integer no) {

		return roomRepo.findByRoomNumber(no);

	}
}
