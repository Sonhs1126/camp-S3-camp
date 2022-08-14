package com.sparta.campproject.service;

import com.amazonaws.services.s3.AmazonS3;
import com.sparta.campproject.dto.CampDto;
import com.sparta.campproject.dto.CampRequestDto;
import com.sparta.campproject.entity.Camp;
import com.sparta.campproject.entity.Member;

//import com.sparta.campproject.mapper.CampMapper;
import com.sparta.campproject.repository.CampRepository;
import com.sparta.campproject.repository.MemberRepository;
import com.sparta.campproject.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor //final로 선언한 변수가 있으면 꼭 생성해달라는 것
@Service
public class CampService {

    private final MemberRepository memberRepository;
    private final CampRepository campRepository;
    private final S3Uploader s3Uploader;
    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

//    public String getNickname() {
//        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<Member> member = memberRepository.findById(Long.valueOf(userId));
//        return member.get().getNickname();
//    }

    public Camp registerCamp(CampRequestDto requestDto, MultipartFile multipartFile) throws IOException {
//        String nickname = getNickname();
        if (multipartFile != null) {
            String imgUrl = s3Uploader.upload(multipartFile);

            Camp camp = Camp.builder()
//                .nickname(nickname)
                    .title(requestDto.getTitle())
                    .location(requestDto.getLocation())
                    .review(requestDto.getReview())
                    .imgUrl(imgUrl)
                    .build();

            campRepository.save(camp);

            return camp;
        }
        Camp camp = Camp.builder()
//                .nickname(nickname)
                .title(requestDto.getTitle())
                .location(requestDto.getLocation())
                .review(requestDto.getReview())
                .build();
        return campRepository.save(camp);
    }



        //S3 필요없는 곳
    public List<CampDto> getCamps() {
        List<Camp> campList = campRepository.findAll();
        List<CampDto> campDtos = new ArrayList<>();
        for (Camp camp : campList) {
//            CampDto campDto = CampMapper.INSTANCE.campToDto(camp);
            campDtos.add(new CampDto(camp));
        }
        return campDtos;
    }

    public Camp showCampDetail(Long campid) {
        return campRepository.findById(campid)
                .orElseThrow(() -> new IllegalArgumentException("해당 추천글이 존재하지 않습니다."));
    }


    @Transactional
    public Camp update(Long id, CampRequestDto requestDto) {
        Camp camp = campRepository.findById(id).orElseThrow( //[3번]  수정할 id에 해당하는 데이터를 repo에서 찾고 해당id를 갖는 memo를 호출한다.
                () -> new IllegalArgumentException("추천글이 존재하지 않습니다")
        );
//        if (!getNickname().equals(camp.getNickname())) {
//            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
//        }
        camp.update(requestDto);
        return camp;
    }

    public boolean delete(Long campid) {
        Camp camp = campRepository.findById(campid).orElseThrow(
                () -> new IllegalArgumentException("메모가 존재하지 않습니다")
        );
//        if (!getNickname().equals(camp.getNickname())) {
//            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
//        }
        campRepository.deleteById(campid);
        return true;
    }

}

