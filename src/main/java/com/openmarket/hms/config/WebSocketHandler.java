package com.openmarket.hms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
public class WebSocketHandler extends TextWebSocketHandler{
	 @Override
	    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	        System.out.println("✅ WebSocket connection established: " + session.getId());
	        session.sendMessage(new TextMessage("Hello, client! Connection is live."));
	    }

	    @Override
	    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	        System.out.println("📩 Received: " + message.getPayload());
	        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
	    }
}
