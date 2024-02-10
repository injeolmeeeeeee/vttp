package vttp.nus.iss.day27workshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class EditedComment implements Serializable {
    
    private int cid;
    private int rating;
    private String comment;
    private LocalDateTime postedDate;

    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDateTime getPostedDate() {
        return postedDate;
    }
    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }
    
    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
                    .add("comment", getComment())
                    .add("rating", getRating())
                    .add("posted", getPostedDate().toString());
    }
    
}
