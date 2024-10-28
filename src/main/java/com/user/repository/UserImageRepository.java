package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
    Optional<UserImage> findById(Integer id);
}