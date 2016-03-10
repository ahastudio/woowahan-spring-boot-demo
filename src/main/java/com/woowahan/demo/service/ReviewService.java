package com.woowahan.demo.service;

import com.woowahan.demo.domain.Review;
import com.woowahan.demo.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Review Service.
 */
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public List<Review> getList() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return reviewRepository.findAll(sort);
    }
}
