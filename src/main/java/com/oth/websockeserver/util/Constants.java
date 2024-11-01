package com.oth.websockeserver.util;

public enum Constants {
	WS_ENDPOINT("/websocket-training"),
	WS_URL("ws://localhost:8878" + WS_ENDPOINT.getValue());

	private final String value;

	Constants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}