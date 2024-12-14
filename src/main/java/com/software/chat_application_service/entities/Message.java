package com.software.chat_application_service.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
	private String sender;
	private String content;
	private LocalDateTime timeStamp;
	
	public Message(String sender,String content) {
		this.sender=sender;
		this.content=content;
		this.timeStamp=LocalDateTime.now();
	}


}
