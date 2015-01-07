/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket.server.test;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 *
 * @author dphillips
 */
public class AdvancedSocketCreator implements WebSocketCreator {

    private Configuration config = null;

    public AdvancedSocketCreator(Configuration config) {
        this.config = config;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        return new Handler(config);
    }
    
}
