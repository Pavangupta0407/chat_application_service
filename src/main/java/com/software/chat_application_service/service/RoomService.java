package com.software.chat_application_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.chat_application_service.entities.Room;
import com.software.chat_application_service.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	
	public Room createRoom(String roomId) {
		Room room = new Room();
		room.setRoomId(roomId);
		roomRepository.save(room);
		return room;
		
	}

}
