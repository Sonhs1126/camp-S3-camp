package com.sparta.campproject.repository;

import com.sparta.campproject.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CampRepository extends JpaRepository<Camp, Long> {
}
