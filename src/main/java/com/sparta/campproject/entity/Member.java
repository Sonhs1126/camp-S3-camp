package com.sparta.campproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.campproject.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member extends Timestamped {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
   private Long id;

   @Column(nullable = false, unique = true)
   private String nickname;

   @Column(nullable = false)
   private String password;
}

