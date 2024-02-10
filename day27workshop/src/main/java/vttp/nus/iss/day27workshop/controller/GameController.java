package vttp.nus.iss.day27workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vttp.nus.iss.day27workshop.model.EditedComment;
import vttp.nus.iss.day27workshop.model.Review;
import vttp.nus.iss.day27workshop.repo.ReviewRepo;
import vttp.nus.iss.day27workshop.service.GameService;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    ReviewRepo reviewRepo;

    //CRUD for review
    //create
    public ResponseEntity<Review> createReview(Review review) {
        Review createdReview = gameService.createReview(review);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdReview);
    }

    //read
    @GetMapping("/review/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable int id) {
        Review review = gameService.getReviewById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(review);
    }

    @PostMapping("/review")
    public ResponseEntity<String> addReview(@RequestBody Review review) {
        
        Review createdReview = gameService.createReview(review);                                                

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdReview.toString());
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable int reviewId,
                                                @RequestBody EditedComment editedComment) {
        editedComment.setCid(reviewId);
        long updatedRows = gameService.updateReview(editedComment);

        if (updatedRows > 0) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.valueOf(updatedRows));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Review not found");
        }
    }

    @GetMapping("/review/{reviewId}/history")
    public ResponseEntity<Review> getHistory(@PathVariable int reviewId){
        Review review = gameService.getReviewById(reviewId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(review);
    }

    //CRUD for game

    // @GetMapping
    // public ResponseEntity<List<Game>> createGame() {
    //     List<Game> games = gameService.getAllGames();

    //     return ResponseEntity
    //             .status(HttpStatus.OK)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .body(games);
    // }

    // @GetMapping
    // public ResponseEntity<List<Game>> createGamePaginated(@RequestParam int limit, 
    //                                                         @RequestParam int offset) {
    //     List<Game> games = gameService.getAllGamesPaginated(limit, offset);

    //     return ResponseEntity
    //             .status(HttpStatus.OK)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .body(games);
    // }
    
    // @GetMapping
    // public ResponseEntity<Long> updateGame(Game game) {
        
    //     long result = gameService.updateGame(game);

    //     return ResponseEntity
    //             .status(HttpStatus.OK)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .body(result);
    // }

    // @GetMapping
    // public ResponseEntity<Long> deleteGame(Game game) {
        
    //     long result = gameService.deleteGameByID(game);

    //     return ResponseEntity
    //             .status(HttpStatus.OK)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .body(result);
    // }
}
