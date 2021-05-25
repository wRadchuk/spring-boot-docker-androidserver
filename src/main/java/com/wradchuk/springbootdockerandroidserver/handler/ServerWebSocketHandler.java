package com.wradchuk.springbootdockerandroidserver.handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wradchuk.springbootdockerandroidserver.service.WebSocketMessageService;
import com.wradchuk.springbootdockerandroidserver.core.Debug;
import com.wradchuk.springbootdockerandroidserver.core.RequestMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

    private String TAG = "AndroidServer sends";
    private List<WebSocketSession> establishedSessions = new CopyOnWriteArrayList<>();

    @Override public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Debug.logInfo("Connection opened");
        establishedSessions.add(session);
        TextMessage message = new TextMessage("Waiting for the command...");
        Debug.logInfo(TAG+ ": " + message);
        session.sendMessage(message);
    }
    @Override public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Debug.logInfo("Connection closed: " + status);
        establishedSessions.remove(session);
    }

    @Override public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       try {
           RequestMessage request = new Gson().fromJson(message.getPayload(), RequestMessage.class);
           WebSocketMessageService service = new WebSocketMessageService();

           Debug.logInfo("Get command: " + request.getCommand());

           switch(request.getCommand()) {
               case START:
                   Debug.logInfo(TAG+ ": " + message);
                   service.getTest(session, request);
               break;
               case END:
               break;
               default: session.sendMessage(new TextMessage(new Gson().toJson("Unknown command")));

           }

       } catch(JsonSyntaxException e) {
        throw new Exception("Invalid message format");
       } catch(Exception e) {
        throw new Exception("Unknown error");
    }
    }


    //@Scheduled(fixedRate = 10000)
    //void sendPeriodicMessages() throws IOException {
    //for (WebSocketSession session : sessions) {
    //if (session.isOpen()) {
    //String broadcast = "server periodic message " + LocalTime.now();
    //Debug.logInfo("Server sends: {} "+ broadcast);
    //session.sendMessage(new TextMessage(broadcast));
    //}
    //}
    //}

    @Override public void handleTransportError(WebSocketSession session, Throwable exception) {
        Debug.logInfo("Server transport error: " + exception.getMessage());
    }
    @Override public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.demo.websocket");
    }
}