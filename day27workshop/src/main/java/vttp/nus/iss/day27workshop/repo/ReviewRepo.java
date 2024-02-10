package vttp.nus.iss.day27workshop.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import vttp.nus.iss.day27workshop.model.Review;

@Repository
public class ReviewRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    // read
    public List<Review> getAllReviews() {
        List<Review> reviews = mongoTemplate.findAll(Review.class);
        return reviews;
    }

    public List<Review> getAllReviewsPaginated(int limit, int offset) {
        Query query = new Query();
        query.skip(limit * offset);
        query.limit(limit);
        List<Review> reviews = mongoTemplate.find(query, Review.class);
        return reviews;
    }

    public Review getReviewById(Integer reviewId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(reviewId));
        
        return mongoTemplate.findOne(query, Review.class);
    }

    // Create
    public Review createReview(Review review) {
        Review createdReview = mongoTemplate.save(review);
        return createdReview;
    }

    // public Review createReview(Review r) {
    //     return mongoTemplate.insert(r, "reviews");
    // }

    // Update
    public long updateReview(Review r) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(r.getReviewId()));

        Update updateOps = new Update()
        .set("rating", r.getRating())
        .set("comment", r.getComment())
        .set("posted", r.getPostedDate())
        .set("edited", r.getEdited());

        UpdateResult result = mongoTemplate.updateMulti(query, updateOps, Review.class, "reviews");
        return result.getMatchedCount();
    }

    // Delete
    public long deleteReview(int gid) {
        Query query = new Query(Criteria.where("_id").is(gid));
        DeleteResult deleteResult = mongoTemplate.remove(query, "reviews");
        return deleteResult.getDeletedCount();
    }
}
