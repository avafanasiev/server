package chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private Session session;
    private static final Logger logger = LogManager.getLogger(ChatWebSocket.class.getName());
    public ChatWebSocket() {
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        logger.info("ChatWebSocket open");
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        logger.info("ChatWebSocket send message");
        sendString(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
    }
}
