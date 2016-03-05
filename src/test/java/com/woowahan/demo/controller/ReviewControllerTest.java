package com.woowahan.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.woowahan.demo.DemoApplication;
import com.woowahan.demo.domain.Review;
import com.woowahan.demo.repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Review Controller Test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DemoApplication.class)
@WebAppConfiguration
public class ReviewControllerTest extends ControllerTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        Review review = new Review();
        review.setName("비룡");
        review.setBody("오늘은 내가 최연소 특급 요리사!");

        reviewRepository.save(review);
    }

    @Test
    public void getList() throws Exception {
        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("비룡")));
    }
}