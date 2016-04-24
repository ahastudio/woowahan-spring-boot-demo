package com.woowahan.demo.api;

import com.woowahan.demo.JerseyConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * REST API Test Base Class.
 */
public abstract class RestTest {

    private static final String BASE_URI = "http://localhost:9999";

    @Autowired
    private ApplicationContext applicationContext;

    private HttpServer server;

    /**
     * 테스트 서버를 띄운다.
     */
    @Before
    public void startServer() {
        ResourceConfig config = new JerseyConfig();
        config.property("contextConfig", applicationContext);
        server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI), config);
    }

    /**
     * 테스트 서버를 내린다.
     */
    @After
    public void stopServer() {
        server.shutdownNow();
    }

    /**
     * API 테스트를 위한 WebTarget 객체를 돌려준다.
     */
    protected WebTarget target(String path) {
        Client client = ClientBuilder.newClient();
        return client.target(BASE_URI).path(path);
    }
}
