package com.sparta.campproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.campproject.Timestamped;
import com.sparta.campproject.dto.CampRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Camp { // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;

    @Column
    private String location;

    @Column(nullable = false)
    private String review;
    // 추가됨
    @Column
    private String imgUrl;
    //
    @Column(nullable = true)
    private String nickname;

    @OneToMany(mappedBy = "camp", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)  //부모가 삭제될 때 자식들도 다 삭제되는 어노테이션
    @JsonManagedReference //DB연관관계 무한회귀 방지
//    @JsonIgnore
    private List<Comment> commentList;

    @ManyToOne()
    @JoinColumn
    @JsonBackReference
    private Member member;

    public void update(CampRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.review = requestDto.getReview();
        this.location = requestDto.getLocation();
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

