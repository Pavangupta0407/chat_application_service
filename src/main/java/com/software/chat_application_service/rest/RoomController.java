package com.software.chat_application_service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.software.chat_application_service.entities.Message;
import com.software.chat_application_service.entities.Room;
import com.software.chat_application_service.repository.RoomRepository;
import com.software.chat_application_service.service.RoomService;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	RoomService roomService;
	
	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody String roomId){
		
		if(roomRepository.findByRoomId(roomId)!=null) {
			return ResponseEntity.badRequest().body("Room already exist");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(roomId));
	}
	
	@GetMapping("/{roomId}")
	public ResponseEntity<?> joinRoom(@PathVariable String roomId){
		Room room = roomRepository.findByRoomId(roomId);
		if(room==null) {
			return ResponseEntity.badRequest().body("Room doesn't exist");
		}
		return ResponseEntity.ok(room);
	}
	
	@GetMapping("/{roomId}/messages")
	public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId,
			@RequestParam(value="page", defaultValue = "0",required=false) int page,
			@RequestParam(value="size", defaultValue = "20",required=false) int size){
		Room room = roomRepository.findByRoomId(roomId);
		if(room==null) {
			return ResponseEntity.badRequest().build();
		}
		List<Message> message = room.getMessage();
		int start = Math.max(0, message.size()-(page+1)*size);
		int end = Math.min(message.size(), start+size);
		List<Message> paginatedMessage = message.subList(start, end);
		return ResponseEntity.ok(paginatedMessage);
		
	}

}
