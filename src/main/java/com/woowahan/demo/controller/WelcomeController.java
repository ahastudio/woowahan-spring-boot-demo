package com.woowahan.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 사이트를 방문한 사람들이 보는 페이지를 처리한다.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    /**
     * GET / 요청을 처리한다.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "Hello";
    }
}
