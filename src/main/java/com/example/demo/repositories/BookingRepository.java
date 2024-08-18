package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Booking;
import com.example.demo.entities.UserMaster;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {


	List<Booking> findByUser(UserMaster user);
}
