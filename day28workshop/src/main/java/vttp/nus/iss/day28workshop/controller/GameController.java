package vttp.nus.iss.day28workshop.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vttp.nus.iss.day28workshop.service.GameService;

@RestControllers
public class GameController {

    @Autowired
    GameService gameService;
    
    @GetMapping("/game/{gid}/reviews")
    public ResponseEntity<List<Document>> getReviewsByGid(@PathVariable int gid) {
        List<Document> comments = gameService.getGameWithReviewByGid(gid);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(comments);
    }

    @GetMapping("/highest")
    public ResponseEntity<GameRatingResponse> getGamesWithHighestRating() {
        List<GameRating> games = gameService.getGamesByRating(true);
        GameRatingResponse response = new GameRatingResponse("highest", games, System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    // Similarly, you can create an endpoint for lowest ratings

    @GetMapping("/lowest")
    public ResponseEntity<GameRatingResponse> getGamesWithLowestRating() {
        List<GameRating> games = gameService.getGamesByRating(false);
        GameRatingResponse response = new GameRatingResponse("lowest", games, System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }


}
