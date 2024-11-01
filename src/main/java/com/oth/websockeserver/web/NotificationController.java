package com.oth.websockeserver.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
public class NotificationController {

	private final SimpMessagingTemplate simpMessagingTemplate;

	public NotificationController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	/**
	 * receive messages from clients /app/hello
	 */
	@MessageMapping("/hello")
	@SendTo("/topic/userid")
	public String greeting(String message) throws Exception {
		System.out.println("received msg " + message);
		Thread.sleep(1000);
		return "Hello this your message " + message  + " 🫵";
	}

	/* Send messages every 15 second */
	@Scheduled(cron = "0/15 * * * * *")
	public void sendNotification() {
		simpMessagingTemplate.convertAndSend("/topic/userid", "Message scheduled - auto generated 😉");
	}
}
