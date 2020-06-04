package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{

}
