package com.subhakar.spring.repository;

import com.subhakar.spring.entity.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
