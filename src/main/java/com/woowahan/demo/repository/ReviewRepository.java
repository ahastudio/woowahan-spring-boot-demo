package com.woowahan.demo.repository;

import com.woowahan.demo.domain.Review;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Review Repository.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {}
