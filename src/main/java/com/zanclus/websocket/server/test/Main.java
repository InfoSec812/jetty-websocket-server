/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket.server.test;

import com.beust.jcommander.JCommander;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author dphillips
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        JCommander parser = new JCommander();
        parser.setProgramName("websocket-test");
        parser.addObject(config);
        try {
            parser.parse(args);
        } catch (Exception e) {
            LOG.error("Error parsing configuration.", e);
            parser.usage();
            return;
        }
    }
}
