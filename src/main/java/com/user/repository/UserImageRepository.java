package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.UserImage;

/**
 * Handles JPA based CRUD operations on User Image upload/view objects
 */
public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
    UserImage findById(UserImage image);
    UserImage findByImageId(String imageid);
}