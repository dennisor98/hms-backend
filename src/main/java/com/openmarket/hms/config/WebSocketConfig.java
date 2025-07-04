package com.openmarket.hms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws") .setAllowedOriginPatterns("*").withSockJS();
		
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic","/queue");
//		registry.enableStompBrokerRelay("/topic", "/queue").setTcpClient(createTcpClient())
//        .setRelayHost("localhost")
//        .setRelayPort(61613)
//        .setClientLogin("ahdev")
//        .setClientPasscode("qwer")
//        .setSystemLogin("ahdev")
//        .setSystemPasscode("qwer")
//        .setVirtualHost("open-hms");
	}
	
//	private ReactorNettyTcpClient<byte[]> createTcpClient() {
//		return new ReactorNettyTcpClient<>(
//				client -> client.remoteAddress(() -> new InetSocketAddress("localhost",61613)).host("localhost"),
//				new StompReactorNettyCodec());
//	}
}

