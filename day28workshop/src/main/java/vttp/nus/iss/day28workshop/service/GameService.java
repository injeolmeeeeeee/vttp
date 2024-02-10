package vttp.nus.iss.day28workshop.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.nus.iss.day28workshop.repo.GameRepo;

@Service
public class GameService {
    
    @Autowired
    GameRepo gameRepo;

    public List<Document> getGameWithReviewByGid(int gid) {
        return gameRepo.getGameWithReviews(gid);
    }
    
    public List<GameRating> getGamesByRating(boolean highest) {
        // Implement logic to query MongoDB and get games with highest or lowest ratings
        // You can use Spring Data MongoDB query methods or Aggregation framework
        return gameRepository.getGamesByRating(highest);
    }

}
