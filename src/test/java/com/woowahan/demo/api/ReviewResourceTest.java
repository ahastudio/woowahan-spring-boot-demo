package com.woowahan.demo.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.woowahan.demo.DemoApplication;
import com.woowahan.demo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Review REST API Test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DemoApplication.class)
@WebAppConfiguration
public class ReviewResourceTest extends RestTest {

    @Test
    public void getList() {
        String responseBody = target("/reviews").request().get(String.class);

        assertThat(responseBody, containsString("우왕ㅋ굳ㅋ"));
    }

    @Test
    public void createReview() {
        Form form = new Form();
        form.param("name", "루피");
        form.param("body", "고무고무");

        Entity<Form> entity = Entity.entity(form,
                MediaType.APPLICATION_FORM_URLENCODED_TYPE);

        Response response = target("/reviews").request().post(entity);

        assertThat(response.getStatus(), equalTo(201));

        Review review = response.readEntity(Review.class);
        assertThat(review.getId(), notNullValue());
        assertThat(review.getName(), equalTo("루피"));
    }

    @Test
    public void getDetail() {
        String responseBody = target("/reviews/17").request().get(String.class);

        assertThat(responseBody, containsString("우왕ㅋ굳ㅋ"));
    }
}
