package com.woowahan.demo.api;

import com.woowahan.demo.api.bean.ReviewBean;
import com.woowahan.demo.domain.Review;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Review REST API.
 */
@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {

    /**
     * Collection GET.
     */
    @GET
    public Response getList() {
        Review review = new Review();
        review.setId(1234L);
        review.setName("리뷰왕");
        review.setBody("우왕ㅋ굳ㅋ");

        List<Review> reviews = new ArrayList<Review>();
        reviews.add(review);

        return Response.ok(reviews).build();
    }

    /**
     * Collection POST.
     */
    @POST
    public Response createReview(@BeanParam ReviewBean reviewParam) {
        Review review = new Review();
        review.setId(1004L);
        review.setName(reviewParam.getName());
        review.setBody(reviewParam.getBody());
        // TODO: save the review.
        return Response.status(Response.Status.CREATED).entity(review).build();
    }

    /**
     * Element GET.
     */
    @GET
    @Path("{id}")
    public Response getReview(@PathParam("id") Long id) {
        Review review = new Review();
        review.setId(id);
        review.setName("리뷰왕");
        review.setBody("우왕ㅋ굳ㅋ");

        return Response.ok().entity(review).build();
    }
}
