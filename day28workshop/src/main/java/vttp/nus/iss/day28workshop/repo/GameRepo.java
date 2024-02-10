package vttp.nus.iss.day28workshop.repo;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import vttp.nus.iss.day28workshop.model.GameWithReviews;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Document> getGameWithReviews(int gameId) {

        // day 28 - slide 9
		// MatchOperation matchOperation =
		// Aggregation.match(Criteria.where("Rated").is("PG"));
		// Aggregation pipeline = Aggregation.newAggregation(matchOperation);
		// AggregationResults<Document> results = mt.aggregate(pipeline, "movies",
		// Document.class);
		// List<Document> docs = results.getMappedResults();
		// System.out.println("Day 28 - slide 9: " + docs.toString());


        MatchOperation matchOperation = Aggregation.match(Criteria.where("gid").is(gameId));
		Aggregation pipeline = Aggregation.newAggregation(matchOperation);
		AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "movies",
		Document.class);
		List<Document> docs = results.getMappedResults();
        return docs;
    }

    public List<GameWithReviews> convertToGameWithReviews(List<Document> docs) {
        return docs.stream()
                .map(this::convertToGameWithReviews)
                .collect(Collectors.toList());
    }
    
    public GameWithReviews convertToGameWithReviews(Document doc) {
        GameWithReviews gameWithReviews = new GameWithReviews();
        gameWithReviews.setId(doc.getString("gid")); // Assuming gid is stored as a String, adjust accordingly
        gameWithReviews.setName(doc.getString("name"));
        gameWithReviews.setYear(doc.getInteger("year"));
        // Map other fields as needed
        return gameWithReviews;
    }

    public List<GameRating> getGamesByRating(boolean highest) {
        AggregationOperation matchOperation = Aggregation.match(Criteria.where("userRating").exists(true));
        AggregationOperation groupOperation = Aggregation.group("gid", "name")
                .first("gid").as("_id")
                .first("name").as("name")
                .max("userRating").as("rating")
                .first("user").as("user")
                .first("comment").as("comment")
                .first("id").as("review_id");

        AggregationOperation sortOperation;
        if (highest) {
            sortOperation = Aggregation.sort(Aggregation.desc("rating"));
        } else {
            sortOperation = Aggregation.sort(Aggregation.asc("rating"));
        }

        Aggregation pipeline = Aggregation.newAggregation(matchOperation, groupOperation, sortOperation);

        AggregationResults<GameRating> results = mongoTemplate.aggregate(pipeline, "game", GameRating.class);
        return results.getMappedResults();
    }

}



