/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket.server.test;

import com.beust.jcommander.JCommander;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author dphillips
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        
        // Parse CLI parameters
        Configuration config = new Configuration();
        JCommander parser = new JCommander();
        parser.setProgramName("websocket-test");
        parser.addObject(config);
        try {
            parser.parse(args);
            if (config.range()>config.idleTimeout()) {
                throw new Exception("The range must be less than or equal to the idle timeout or the server may disconnect unexpectedly");
            }
        } catch (Exception e) {
            LOG.error("Error parsing configuration.", e);
            parser.usage();
            return;
        }

        InetSocketAddress bind = new InetSocketAddress(config.address(), config.port());
        if (!bind.isUnresolved()) {
        // Create a Jetty server instance
            Server server = new Server(bind);
            
            ServletContextHandler sch = new ServletContextHandler();
            sch.addServlet(new ServletHolder(new RandomWebSocketServlet(config)), config.path());
            
            server.setHandler(sch);
            try {
                server.start();
                server.join();
            } catch (Exception ex) {
                LOG.error("Unable to start Jetty Servlet Container", ex);
            }
        } else {
            LOG.error("Unable to resolve bind address: "+config.address());
        }
    }
}
