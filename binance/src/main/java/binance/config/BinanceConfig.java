package binance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import binance.handler.BinanceHandler;

@Configuration
@EnableWebSocket
@Controller
public class BinanceConfig implements WebSocketConfigurer {
	
	@Autowired
	private BinanceHandler webSocketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/binance/list").setAllowedOrigins("*");
    }
}
