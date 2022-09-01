package com.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.models.MatchingScoreModel;

public interface MatchingRepo extends JpaRepository<MatchingScoreModel, Long>{

}
