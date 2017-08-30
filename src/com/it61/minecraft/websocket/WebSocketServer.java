package com.it61.minecraft.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketServer {
	
	private static ConcurrentHashMap<Integer,Session> connections = new ConcurrentHashMap<Integer,Session>();
	private Session session;
	private Integer userId;
	
	/**
	 * 建立连接时调用
	 * @param session
	 */
	@OnOpen
	public void start(@PathParam("userId")Integer userId,Session session){
		System.out.println("WebSocketServer服务端 用户"+userId+"连接成功");

		this.userId = userId;
		this.session = session;
		connections.put(userId, session);
		
		try {
			String msg = "你好  "+this.userId;
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 连接断开时调用
	 */
	 @OnClose
	 public void end() {
		 System.out.println("WebSocketServer服务端 用户"+userId+"断开连接");
		 connections.remove(this.userId);
	 }
	 
	/**
	 * 收到客户端发送的消息时调用
	 * @param message
	 */
	@OnMessage
    public void incoming(String message) {
		
    }
	
	/**
	 * 发生错误时调用
	 * @param t
	 * @throws Throwable
	 */
    @OnError
    public void onError(Throwable t) throws Throwable {
    	t.printStackTrace();
        System.out.println("Chat Error: " + t.toString());
    }
    


}
