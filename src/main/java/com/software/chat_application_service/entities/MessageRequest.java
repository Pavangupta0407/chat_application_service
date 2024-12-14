package com.software.chat_application_service.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

	private String sender;
	private String content;
	private String roomId;
	private LocalDateTime messageTime;
}
