package com.woowahan.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import com.woowahan.demo.domain.Review;
import com.woowahan.demo.repository.ReviewRepository;

import org.junit.Before;
import org.junit.Test;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Review Service Test.
 */
public class ReviewServiceTest {

    private static final String[] REVIEW_NAMES = {"비룡", "쇼타로", "소마"};

    private ReviewService reviewService;

    /**
     * ReviewService 준비.
     */
    @Before
    public void setUp() {
        List<Review> reviews = createReviews()
                .sorted((review1, review2) ->
                        review1.getId() > review2.getId() ? -1 : 1)
                .collect(Collectors.toList());

        ReviewRepository reviewRepository = mock(ReviewRepository.class);

        given(reviewRepository.findAll(any(Sort.class))).willReturn(reviews);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void getList() {
        List<Review> review = reviewService.getList();

        assertThat(review.get(0).getId()).isGreaterThan(review.get(1).getId());
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