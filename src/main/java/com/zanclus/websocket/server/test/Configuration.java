/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket.server.test;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

/**
 *
 * @author dphillips
 */
@Parameters(separators = "= ")
@Data
public class Configuration {
    
    @Parameter(names = {"-p", "--port"}, description = "The TCP port to bind to")
    private Integer port = 8000;

    @Parameter(names = {"-b", "--bind"}, description = "The IP address/interface to bind to")
    private String address = "127.0.0.1";

    @Parameter(names = {"-u", "--path"}, description = "The path on which to accept WebSocket connections")
    private String path = "/websocket";

    @Parameter(names = {"-r", "--range"}, description = "The range for the randomized timer between which message will be sent (in milliseconds)")
    private Integer range = 2000;

    @Parameter(names = {"-i", "--idle-timeout"}, description = "Amount of time an idle websocket will be kept before disconnecting")
    private Integer idleTimeout = 5000;
}
