/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket.server.test;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 *
 * @author dphillips
 */
@WebSocket
@Slf4j
public class Handler {

    protected Configuration config = null;

    protected Session session = null;

    private class RandomIntervalMessageSender extends Thread {

        @Override
        public void run() {
            while(true) {
                long delay = Math.round(Math.random()*config.range());
                try {
                    Thread.sleep(delay);
                    session.getRemote().sendString("Random Message: "+System.currentTimeMillis());
                } catch (InterruptedException | IOException e) {
                    LOG.error("Error sending randomly timed message", e);
                }
            }
        }
        
    }

    public Handler(Configuration config) {
        this.config = config;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        LOG.info("WebSocket connected to client: "+session.getRemoteAddress().getHostName()+":"+session.getRemoteAddress().getPort());
        this.session = session;
        if (session.isOpen()) {
            try {
                session.getRemote().sendString("WebSocket connected");
                Thread messagingThread = new RandomIntervalMessageSender();
                messagingThread.start();
            } catch (IOException ioe) {
                LOG.error("Exception sending initial connect message", ioe);
            }
        }
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        LOG.error("WebSocket error", t);
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        LOG.info("Message: "+message);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        LOG.info("WebSocket closed: "+statusCode+" - "+reason);
    }
}
