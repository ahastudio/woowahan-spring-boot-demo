package com.woowahan.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.woowahan.demo.domain.Review;
import com.woowahan.demo.service.ReviewService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Review Controller Test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReviewControllerTest extends ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ReviewService reviewService;

    /**
     * ReviewService를 Mock으로 처리.
     */
    @Before
    public void setUp() {
        Review review = new Review();
        review.setName("비룡");
        review.setBody("오늘은 내가 최연소 특급 요리사!");

        List<Review> reviews = new ArrayList<Review>();
        reviews.add(review);

        given(reviewService.getList()).willReturn(reviews);
    }

    @Test
    public void getList() throws Exception {
        ResponseEntity<String> response =
                restTemplate.getForEntity("/reviews", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("비룡");
    }
}
