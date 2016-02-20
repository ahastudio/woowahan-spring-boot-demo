package com.woowahan.demo.api.bean;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.FormParam;

/**
 * Review Bean.
 */
@Getter
@Setter
public class ReviewBean {

    @FormParam("name")
    private String name;

    @FormParam("body")
    private String body;
}
