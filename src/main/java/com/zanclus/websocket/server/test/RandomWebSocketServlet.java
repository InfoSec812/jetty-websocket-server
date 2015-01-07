/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket.server.test;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 *
 * @author dphillips
 */
public class RandomWebSocketServlet extends WebSocketServlet {

    private Configuration config = null;

    public RandomWebSocketServlet(Configuration config) {
        this.config = config;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(config.idleTimeout());
        factory.setCreator(new AdvancedSocketCreator(config));
    }
    
}
