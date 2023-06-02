package hjh.capstone.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer
{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        // WebSocket 엔드포인트 등록
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry)
    {
        // 메시지 브로커 구성
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}

//import org.springframework.web.reactive.socket.WebSocketHandler;
//import org.springframework.web.reactive.socket.WebSocketSession;
//import reactor.core.publisher.Mono;
//
//public class NotificationWebSocketHandler implements WebSocketHandler
//{
//
//    private final String vapidPublicKey;
//    private final String vapidPrivateKey;
//
//    public NotificationWebSocketHandler(String vapidPublicKey, String vapidPrivateKey) {
//        this.vapidPublicKey = vapidPublicKey;
//        this.vapidPrivateKey = vapidPrivateKey;
//    }
//
//    @Override
//    public Mono<Void> handle(WebSocketSession session) {
//        // WebSocket 세션 처리 로직 작성
//        return session.send(...)
//            .then(session.receive()
//                .doOnNext(...)
//                .then());
//    }
//}
