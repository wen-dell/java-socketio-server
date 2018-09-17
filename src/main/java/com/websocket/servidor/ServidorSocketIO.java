package com.websocket.servidor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class ServidorSocketIO extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
    	
    	Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setOrigin("http://localhost:4200");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("canal", String.class, new DataListener<String>() {
            public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
                server.getBroadcastOperations().sendEvent("canal", data);
            }
        });

        server.start();

    }
    
    @Override
    public void destroy() {
    	
    }
	
}
