package com.oth.client;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static com.oth.websockeserver.util.Constants.WS_URL;

/**
 * Stand alone WebSocketStompClient.
 */
public class StompClient {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		//stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		stompClient.setMessageConverter(new StringMessageConverter());

		StompSessionHandler sessionHandler = new StompClientSessionHandler();
		StompSession stompSession = stompClient.connectAsync(WS_URL.getValue(), sessionHandler).get();

		while (true) {
			Scanner scanner = new Scanner(System.in);
			String msg = scanner.nextLine();
			stompSession.send("/app/hello", "Message from Java-Spring Client 😶‍🌫️ " + msg);
			stompSession.send("/topic/users", "Message from Java-Spring Client 😶‍🌫️ " + msg);

		}
	}
}
