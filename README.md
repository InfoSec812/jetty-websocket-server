Jetty WebSocket Server
======================

A simple Jetty 9.2.x based WebSocket server which just sends periodic JSON
messages on a randomized timer.

Prerequisites
-------------

1. Maven 3.x
1. Java >= 1.7

Running
-------

    mvn java:exec -Dexec.args="-b 0.0.0.0 -u /websocket -r 3000 -i 5000"

Command-Line Options
--------------------
    Usage: websocket-test [options]
      Options:
        -b, --bind
           The IP address/interface to bind to
           Default: 127.0.0.1
        -i, --idle-timeout
           Amount of time an idle websocket will be kept before disconnecting
           Default: 5000
        -u, --path
           The path on which to accept WebSocket connections
           Default: /websocket
        -p, --port
           The TCP port to bind to
           Default: 8000
        -r, --range
           The range for the randomized timer between which message will be sent (in
           milliseconds)
           Default: 2000

