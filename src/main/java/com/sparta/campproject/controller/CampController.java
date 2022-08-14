package com.sparta.campproject.controller;

import com.sparta.campproject.dto.CampDto;
import com.sparta.campproject.dto.CampRequestDto;
import com.sparta.campproject.entity.Camp;
import com.sparta.campproject.repository.CampRepository;
import com.sparta.campproject.s3.S3Uploader;
import com.sparta.campproject.service.CampService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CampController {

    private final CampService campService;
    //private final CampRepository campRepository;
    private final S3Uploader s3Uploader;
    private MultipartFile multipartFile;
//    @PostMapping(value = "/api/auth/camp", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})


//    @Secured("ROLE_USER")
    @PostMapping(value = "/api/camp", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Camp registerCamp(@RequestPart(value= "dto") CampRequestDto requestDto,
                             @RequestPart(required = false) MultipartFile multipartFile) throws IOException {

        return campService.registerCamp(requestDto,multipartFile);
    }

    @GetMapping("/api/camp")
    public List<CampDto> getCamps() {
        return campService.getCamps();
    }

    @GetMapping("/api/camp/{campid}")
    public Camp showCampDetail(@PathVariable Long campid) {
        return campService.showCampDetail(campid);
    }

//    @Secured("ROLE_USER")
    @PutMapping("/api/auth/camp/{campid}")
    public Camp updateMemo(@PathVariable Long campid, @RequestBody CampRequestDto requestDto) {   //RequestBody어노테이션을 써줘야만 Request 안에 Body를 requestDto에 넣어줘야하구나 를 Spring이 안다
        return campService.update(campid, requestDto);
    }

//    @Secured("ROLE_USER")
    @DeleteMapping("/api/auth/camp/{campid}")
    public boolean deleteMemo(@PathVariable Long campid) {   //RequestBody어노테이션을 써줘야만 Request 안에 Body를 requestDto에 넣어줘야하구나 를 Spring이 안다
        return campService.delete(campid);
    }


    @PostMapping("/api/auth/camp/{campid}")
    public String upload(@PathVariable Long campid, MultipartFile multipartFile) throws IOException {

        return s3Uploader.upload(multipartFile);
    }
}