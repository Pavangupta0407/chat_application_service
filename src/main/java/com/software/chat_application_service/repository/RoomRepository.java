package com.software.chat_application_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.software.chat_application_service.entities.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room,String>{
	
	Room findByRoomId(String roomId);

}
