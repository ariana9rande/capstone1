package hjh.capstone.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class WebSocketConfig
{
    @Autowired
    private WebSocketMessageChannel webSocketMessageChannel;

    @Bean
    public SimpMessagingTemplate simpMessagingTemplate()
    {
        return new SimpMessagingTemplate(webSocketMessageChannel);
    }
}
