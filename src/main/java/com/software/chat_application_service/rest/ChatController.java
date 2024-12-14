package com.software.chat_application_service.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.software.chat_application_service.entities.Message;
import com.software.chat_application_service.entities.MessageRequest;
import com.software.chat_application_service.entities.Room;
import com.software.chat_application_service.repository.RoomRepository;

@Controller
public class ChatController {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@MessageMapping("/sendmessage/{roomId}") // /app/sendMessage/roomid message wil be sent
	@SendTo("/topic/room/{roomId}") //Subscribe to topic
	public Message sendMessage(
			@DestinationVariable String roomId,
			@RequestBody MessageRequest payload) {
		Room room = roomRepository.findByRoomId(payload.getRoomId());
		Message message = new Message();
		message.setContent(payload.getContent());
		message.setSender(payload.getSender());
		message.setTimeStamp(LocalDateTime.now());
		if(room!=null) {
			room.getMessage().add(message);
			roomRepository.save(room);
		} else {
			throw new RuntimeException("Room not exist");
		}
		return message;
		
	}

}
