package vttp.nus.iss.day28workshop.model;

import java.time.LocalDate;
import java.util.List;

public class GameWithReviews extends Game {

    private List<String> reviews;
    private LocalDate timestamp;
    
    public List<String> getReviews() {
        return reviews;
    }
    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
    public LocalDate getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }   
}
