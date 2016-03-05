package com.woowahan.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Review Entity.
 */
@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String body;
}
