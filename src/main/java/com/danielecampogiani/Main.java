package com.danielecampogiani;

import com.sun.net.httpserver.HttpServer;
import org.cybergarage.upnp.UPnP;
import org.cybergarage.upnp.device.InvalidDescriptionException;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) {

        UPnP.setEnable(UPnP.USE_ONLY_IPV4_ADDR);
        //org.cybergarage.util.Debug.on();

        MacDevice thisMac = null;

        try{
            thisMac = new MacDevice();

        }catch (InvalidDescriptionException e){
            System.out.println("InvalidDescription = "+e.getMessage());
        }

        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(9000),0);
            httpServer.createContext("/",new MyHTTPServer());
            httpServer.start();



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
