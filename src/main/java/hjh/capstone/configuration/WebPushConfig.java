//package hjh.capstone.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
//import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
//import org.springframework.web.socket.WebSocketHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class WebPushConfig implements WebFluxConfigurer {
//
//    @Value("${spring.webpush.vapid.public-key}")
//    private String vapidPublicKey;
//
//    @Value("${spring.webpush.vapid.private-key}")
//    private String vapidPrivateKey;
//
//    @Bean
//    public SimpleUrlHandlerMapping webSocketHandlerMapping() {
//        Map<String, Object> urlMap = new HashMap<>();
//        urlMap.put("/ws", webSocketHandler());
//
//        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//        handlerMapping.setUrlMap(urlMap);
//        handlerMapping.setOrder(10);
//
//        return handlerMapping;
//    }
//
//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        return new WebSocketHandlerAdapter();
//    }
//
//    @Bean
//    public WebSocketHandler webSocketHandler() {
//        return new NotificationWebSocketHandler(vapidPublicKey, vapidPrivateKey);
//    }
//}
