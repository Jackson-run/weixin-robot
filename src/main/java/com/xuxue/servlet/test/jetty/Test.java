package com.xuxue.servlet.test.jetty;

import org.eclipse.jetty.server.Server;

/**
 * Created by xuxue on 16-11-18.
 */
public class Test {

    public static void main(String[] args)throws Exception{
        Server server = new Server(12345);
        server.setHandler(new HelloHandler());
        server.start();
        server.dumpStdErr();
        server.join();
    }

}
