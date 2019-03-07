package com.example.myowngame;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final String STOMP_BROKER_ENDPOINT = "/mog-socket";
    private static final String STOMP_BROKER_TOPIC_PREFIX = "/topic";
    private static final String STOMP_BROKER_APP_DESTINATION_PREFIX = "/app";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(STOMP_BROKER_TOPIC_PREFIX);
        config.setApplicationDestinationPrefixes(STOMP_BROKER_APP_DESTINATION_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(STOMP_BROKER_ENDPOINT).withSockJS();
    }

}
