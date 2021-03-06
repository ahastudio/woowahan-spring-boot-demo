package com.woowahan.demo.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.woowahan.demo.domain.Review;
import com.woowahan.demo.repository.ReviewRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Review REST API Test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewResourceTest extends RestTest {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Review Fixture 설정.
     */
    @Before
    public void setUp() {
        Review review = new Review();
        review.setName("비룡");
        review.setBody("오늘은 내가 최연소 특급 요리사!");

        reviewRepository.save(review);
    }

    @Test
    public void getList() {
        Response response = target("/reviews").request().get();

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);

        assertThat(response.readEntity(String.class)).contains("비룡");
    }

    @Test
    public void createReview() {
        Form form = new Form();
        form.param("name", "루피");
        form.param("body", "고무고무");

        Entity<Form> entity = Entity.entity(form,
                MediaType.APPLICATION_FORM_URLENCODED_TYPE);

        Response response = target("/reviews").request().post(entity);

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.CREATED);

        Review review = response.readEntity(Review.class);
        assertThat(review.getId()).isNotNull();
        assertThat(review.getName()).isEqualTo("루피");
    }

    @Test
    public void getDetail() {
        String responseBody = target("/reviews/17").request().get(String.class);

        assertThat(responseBody).contains("우왕ㅋ굳ㅋ");
    }
}
