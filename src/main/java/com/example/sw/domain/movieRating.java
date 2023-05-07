package com.example.sw.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class movieRating implements Serializable {
    // implements Serializable: 객체 직렬화
    // 아무래도 User에 있는 userId를 외래키 + 기본키로 사용하다 보니 이렇게 된듯.....

    @Id
    @ManyToOne(fetch = FetchType.LAZY) // fetch = FetchType.LAZY: 엔티티를 조회할 때, 연관된 엔티티는 실제 사용 시점에 조회한다.
    @JoinColumn(name = "userId") // 외래키 이름
    private User user; // 일단 객체를 만들어서 끄집어 내는 방식인듯....?

    @Column(nullable = false)
    private Long movieId;

    @Column(nullable = false)
    private double rating;
}
