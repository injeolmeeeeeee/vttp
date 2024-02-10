package vttp.nus.iss.day27workshop.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import vttp.nus.iss.day27workshop.model.Game;

@Repository
public class GameRepo {
    
    @Autowired
    MongoTemplate mongoTemplate;

    //CRUD

    public List<Game> getAllGames() {
        List<Game> games = mongoTemplate.findAll(Game.class);
        return games;
    }

    public List<Game> getAllGamesPaginated(int limit, int offset) {
        Query query = new Query();
        query.skip(limit * offset);
        query.limit(limit);
        List<Game> games = mongoTemplate.find(query, Game.class);
        return games;
    }

    public List<Game> getAllGamesPaginated2(int limit, int offset) {
        Query query = new Query();
        Pageable pageable = PageRequest.of(offset,limit);
        query.with(pageable);
        List<Game> games = mongoTemplate.find(query, Game.class);
        return games;
    }

    public Game createGame(Game game) {
        Game createdGame = mongoTemplate.save(game);
        return createdGame;
    }

    public long createGame2(Game game) {
        //check if game exist
        Query query = Query.query(Criteria.where("_id").is(game.getGid()));
        Update update = new Update()
                        .set("name", game.getName())
                        .set("rating", game.getRating())
                        .set("userRating", game.getUserRating())
                        .set("year", game.getYear());
        UpdateResult result = mongoTemplate.updateMulti(query, update, Game.class, "games");
        return result.getModifiedCount();
    }

    public long deleteGame(int gid) {
        Query query = new Query(Criteria.where("_id").is(gid));
        DeleteResult deleteResult = mongoTemplate.remove(query, "games");
        return deleteResult.getDeletedCount();
    }

    public List<Game> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return mongoTemplate.find(query, Game.class);
    }


    public Game findById(Integer id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoTemplate.findOne(query, Game.class, "games");
    }



}
