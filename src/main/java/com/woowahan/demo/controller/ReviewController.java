package com.woowahan.demo.controller;

import com.woowahan.demo.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Review Controller.
 */
@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    /**
     * Collection GET.
     */
    @GetMapping
    public String index(ModelMap model) {
        model.put("reviews", reviewService.getList());

        return "review/index";
    }
}
