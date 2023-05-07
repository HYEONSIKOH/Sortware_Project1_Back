package com.example.sw.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
// Entity 메소드에서는 절대로 setter 메소드를 만들지 않는다!
// getter, setter를 무작정 생성하는 경우 클래스의 인스턴스 값들이 언제 변경되는지 명확하게 알 수 없다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 45, nullable = false)
    private String id;

    @Column(length = 45, nullable = false)
    private String pw;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 45, nullable = false)
    private String email;
}
