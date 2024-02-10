package vttp.nus.iss.day27workshop.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.nus.iss.day27workshop.model.EditedComment;
import vttp.nus.iss.day27workshop.model.Game;
import vttp.nus.iss.day27workshop.model.Review;
import vttp.nus.iss.day27workshop.repo.GameRepo;
import vttp.nus.iss.day27workshop.repo.ReviewRepo;

@Service
public class GameService {
    
    @Autowired
    GameRepo gameRepo;

    @Autowired
    ReviewRepo reviewRepo;

    public List<Game> getAllGames() {
        return gameRepo.getAllGames();
    }

    public List<Game> getAllGamesPaginated(int limit, int offset) {
        return gameRepo.getAllGamesPaginated(limit, offset);
    }

    public long updateGame(Game game) {
        return gameRepo.createGame2(game);
    }

    public long deleteGameByID(Game game) {
        return gameRepo.deleteGame(game.getGid());
    }

    public Review getReviewById(Integer id) {
        return reviewRepo.getReviewById(id);
    }

    public Review createReview(Review review){
        Game game = gameRepo.findById(review.getGameId());
        if (game == null) {
            System.out.println("Game not found");
        }

        review.setGameName(game.getName());
        review.setPostedDate(LocalDateTime.now());

        return reviewRepo.createReview(review);
    }

    public long updateReview(EditedComment ec) {
        Review result = reviewRepo.getReviewById(ec.getCid());
        List<EditedComment> list = result.getEdited();

        if (result.getEdited() == null) {
            list = new ArrayList<>();
            result.setEdited(list);
        }

        EditedComment e = new EditedComment();
        e.setComment((result.getComment()));
        e.setRating(result.getRating());
        e.setPostedDate(result.getPostedDate());

        return reviewRepo.updateReview(result);
    }

    public List<Review> getAllReviews() {
        return reviewRepo.getAllReviews();
    }

}
