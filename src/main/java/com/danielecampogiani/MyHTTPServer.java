package com.danielecampogiani;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by danielecampogiani on 04/06/14.
 */
public class MyHTTPServer implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type","image/jpeg");
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        File image = new File("images"+uri.getPath());
        FileInputStream inputStream = new FileInputStream(image);
        final byte[] buffer = new byte[0x10000];
        int count = 0;
        while ((count = inputStream.read(buffer)) >= 0) {
            os.write(buffer,0,count);
        }
        os.close();

    }
}
