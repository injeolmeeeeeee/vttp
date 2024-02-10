package vttp.nus.iss.day27workshop.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Game implements Serializable{
    
    @Id
    private int gid;
    private String name;
    private int year;
    private int rating;
    private int userRating;

    public Game() {}

    public Game(int gid, String name, int year, int rating, int userRating) {
        this.gid = gid;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.userRating = userRating;
    }

    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getUserRating() {
        return userRating;
    }
    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    @Override
    public String toString() {
        return "Game [gid=" + gid + ", name=" + name + ", year=" + year + ", rating=" + rating + ", userRating="
                + userRating + "]";
    }
}
