package com.woowahan.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.woowahan.demo.DemoApplication;
import com.woowahan.demo.domain.Review;
import com.woowahan.demo.repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Review Service Test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DemoApplication.class)
@Transactional
public class ReviewServiceTest {

    private static final String[] REVIEW_NAMES = {"비룡", "쇼타로", "소마"};

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<Review> reviews = createReviews()
                .sorted((a, b) -> a.getId() > b.getId() ? -1 : 1)
                .collect(Collectors.toList());

        when(reviewRepository.findAll(any(Sort.class))).thenReturn(reviews);
    }

    @Test
    public void getList() {
        List<Review> review = reviewService.getList();

        assertThat(review.get(0).getId(), greaterThan(review.get(1).getId()));
    }

    private Stream<Review> createReviews() {
        return IntStream.range(0, REVIEW_NAMES.length)
                .mapToObj(index -> {
                    Review review = new Review();
                    review.setId(index + 1L);
                    review.setName(REVIEW_NAMES[index]);
                    review.setBody("좋아요");
                    return review;
                });
    }
}