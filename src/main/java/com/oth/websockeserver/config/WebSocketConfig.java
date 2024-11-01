package com.oth.websockeserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static com.oth.websockeserver.util.Constants.WS_ENDPOINT;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic"); //To Push Event directly on broker without passing by backend app
		config.setApplicationDestinationPrefixes("/app"); //this a prefix to combine it with @MessageMapping("/hello") in this case we should push event on host/app/hello
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(WS_ENDPOINT.getValue())
				.setAllowedOriginPatterns("*");
	}

}
