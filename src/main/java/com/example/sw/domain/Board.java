package com.example.sw.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@NoArgsConstructor
@Entity
@IdClass(BoardId.class)
@Getter
public class Board implements Serializable {
    @Id
    @Column(nullable = false)
    private long userid;

    @Id
    @Column(nullable = false)
    private long movieid;

    @Column(nullable = false)
    private double rating;

    @Column(length = 80)
    private String comment;

    @Column @JsonSerialize @JsonDeserialize
    private LocalDateTime date;

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

    public Board(long userid, long movieid, double rating, String comment, LocalDateTime date){
        this.userid = userid;
        this.movieid = movieid;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Board{" +
                "movieId=" + movieid +
                ", userId=" + userid +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                '}';
    }
}
