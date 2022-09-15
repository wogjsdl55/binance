package binance.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class BinanceHandler extends TextWebSocketHandler{
	  
    private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	private static final Logger log = LoggerFactory.getLogger(BinanceHandler.class);

    
    //클라이언트가 연결 되었을 때 실행
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	log.info(session.getId() + " 연결성공");
    	users.put(session.getId(), session);
    }
    
    //클라이언트가 연결을 끊었을 때 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	log.info(session.getId() + " 연결종료");
    	users.remove(session.getId());
    }
    

  //연결된 클라이언트에서 예외 발생 시 실행
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("exception==============="+ exception.getMessage());
    }
    
    
    //클라이언트가 서버로 메세지를 전송했을 때 실행
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	log.info(session.getId() + "님의 송신 메시지 " + message.getPayload());
    	
    }
}
