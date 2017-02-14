package com.sunsw.mercury.system.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by sunsw on 2017/1/23.
 */
@Component
public class MercuryWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        //接收到客户端消息时调用
//        System.out.println("text message: " + session.getId() + "-" + message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession socketSession) throws Exception {
        // 与客户端完成连接后调用
//        System.out.println("afterConnectionEstablished");
//        System.out.println("getId:" + socketSession.getId());
//        System.out.println("getLocalAddress:" + socketSession.getLocalAddress().toString());
//        System.out.println("getTextMessageSizeLimit:" + socketSession.getTextMessageSizeLimit());
//        System.out.println("getUri:" + socketSession.getUri().toString());
//        System.out.println("getPrincipal:" + socketSession.getPrincipal());
//        socketSession.sendMessage(new TextMessage("你好".getBytes()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 消息传输出错时调用
//        System.out.println("handleTransportError");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 一个客户端连接断开时关闭
//        System.out.println("afterConnectionClosed");
    }

}
