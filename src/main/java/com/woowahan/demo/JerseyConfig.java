package com.woowahan.demo;

import com.woowahan.demo.api.ReviewResource;
import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

/**
 * Jersey Configuration.
 */
@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ReviewResource.class);
    }
}
