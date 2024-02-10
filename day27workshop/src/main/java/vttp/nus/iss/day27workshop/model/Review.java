package vttp.nus.iss.day27workshop.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@Document(collection = "reviews")
public class Review extends EditedComment{

    @Id
    private Integer reviewId;
    private Integer gameId;
    private String gameName;
    private String user;
    private List<EditedComment> edited;

    public Integer getReviewId() {return reviewId;}
    public void setReviewId(Integer reviewId) {this.reviewId = reviewId;}

    public Integer getGameId() {return gameId;}
    public void setGameId(Integer gameId) {this.gameId = gameId;}

    public String getGameName() {return gameName;}
    public void setGameName(String gameName) {this.gameName = gameName;}

    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}

    public List<EditedComment> getEdited() {return edited;}
    public void setEdited(List<EditedComment> edited) {this.edited = edited;}

        public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("gid", getGameId())
                .add("comment", getComment())
                .add("rating", getRating())
                .add("user", getUser())
                .add("posted", getPostedDate().toString())
                .add("cid", getCid())
                .build();
    }
    
    //convert individual comments to JSON
    public JsonObject EditedCommentToJSON() {
        boolean isEditedComment = false;
        //if there are edited comments already
        if (this.getEdited() != null) {
            List<JsonObjectBuilder> editComments = this.getEdited()
                    .stream()
                    //run every comment through toJSON to convert them to JSON object
                    .map(t -> t.toJSON())
                    .toList();

            if (editComments.size() > 0) {
                isEditedComment = true;
            }
        }
        //else if there are no edited comments, 
        return Json.createObjectBuilder()
                .add("gid", getGameId())
                .add("comment", getComment())
                .add("rating", getRating())
                .add("user", getUser())
                .add("posted", getPostedDate().toString())
                .add("name", getGameName())
                .add("edited", isEditedComment)
                .build();
    }

    //convert 
    public JsonObject toJsonEditedList() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> editComments = this.getEdited()
                .stream()
                .map(t -> t.toJSON())
                .toList();

        for (JsonObjectBuilder j : editComments) {
            arrBuilder.add(j);
        }

        return Json.createObjectBuilder()
                .add("gid", getGameId())
                .add("comment", getComment())
                .add("rating", getRating())
                .add("user", getUser())
                .add("posted", getPostedDate().toString())
                .add("name", getGameName())
                .add("edited", arrBuilder)
                .build();
    }
}
