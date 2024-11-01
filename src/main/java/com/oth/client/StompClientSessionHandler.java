package com.oth.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class StompClientSessionHandler extends StompSessionHandlerAdapter {

	private Logger logger = LogManager.getLogger(StompClientSessionHandler.class);

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		logger.info("New session established: {}",  session.getSessionId());
		session.subscribe("/topic/userid", this);
		logger.info("Subscribed to /topic/userid");
		session.send("/topic/userid", "Hello From Java Client");
		logger.info("Message sent to websocket server");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
		logger.error("Got an exception", exception);
	}

	@Override
	public Type getPayloadType(@NonNull StompHeaders headers) {
		return String.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		logger.info("Received : {}", payload);
	}

}