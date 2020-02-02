package afanasievald.chat;

import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import afanasievald.services.LogService;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private Session session;
    private Logger logger;
    public ChatWebSocket() {
        logger = LogService.getLogger(ChatWebSocket.class.getName());
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        logger.info("ChatWebSocket open");
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        logger.info("ChatWebSocket send message");
        sendString(data); // send only to self
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage()); //dont use it in prod
            logger.error(e.getMessage());
        }
    }
}
