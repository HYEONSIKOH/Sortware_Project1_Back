package com.example.sw.dto;

import com.example.sw.domain.Board;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class BoardForm {
    private long userid;
    private long movieid;
    private double rating;

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setMovieid(long movieid) {
        this.movieid = movieid;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private String comment;
    private LocalDateTime date;

    @JsonCreator
    public BoardForm (@JsonProperty("userid") long userid,
                      @JsonProperty("movieid") long movieid,
                      @JsonProperty("rating") double rating,
                      @JsonProperty("comment") String comment,
                      @JsonProperty("date") LocalDateTime date) {
        this.userid = userid;
        this.movieid = movieid;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = date.format(formatter);

        return "UserId= " + userid +
                " MovieId= " + movieid +
                " comment= " + comment +
                " Rating= " + rating +
                " Date= " + formattedDateTime;
    }

    public Board toEntity() {
        return new Board(userid, movieid, rating, comment, date);
    }
}
