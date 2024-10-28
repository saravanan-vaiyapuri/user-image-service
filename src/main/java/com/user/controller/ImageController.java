package com.user.controller;

import com.user.model.Image;
import com.user.model.ResponseUserData;
import com.user.model.User;
import com.user.service.ImageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

  @Autowired
  private ImageService imageService;

  @GetMapping("images")
  public ResponseUserData getImages() {
    LOGGER.info("getImages called for current active session user");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = (User) authentication.getPrincipal();
    return imageService.getImages(currentUser);
  }

  @GetMapping("image/{id}")
  public ResponseUserData getImage(@PathVariable("id") String id) {
    LOGGER.info("getImage called for id {}", id);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = (User) authentication.getPrincipal();
    return imageService.getImage(id,currentUser);
  }
  
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Image uploadFile(@RequestParam("title") String title, @RequestParam("type") String type,@RequestParam("description") String description,@RequestParam("image") MultipartFile file ) throws Exception {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User currentUser = (User) authentication.getPrincipal();
	  // Process the file and form data
	  return imageService.uploadImage(file,type,title,description,currentUser);
     
  }
}