package hjh.capstone.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class WebSocketConfig
{
    @Bean
    public SimpMessagingTemplate messagingTemplate()
    {
        return new SimpMessagingTemplate(clientOutboundChannel());
    }

    @Bean
    public MessageChannel clientOutboundChannel()
    {
        return new DirectChannel();
    }
}
