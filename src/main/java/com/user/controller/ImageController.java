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

/**
 * This controller is used to Image upload, view and read all the images
 * attached to an active session user
 */
@RestController
public class ImageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private ImageService imageService;

	/**
	 * This method is used to read all images attached to the active session user
	 * profile
	 * 
	 * @return ResponseUserData
	 */
	@GetMapping("images")
	public ResponseUserData getImages() {
		LOGGER.info("getImages called for current active session user");
		return imageService.getImages(validateUserCredential());
	}

	/**
	 * This method is used to read the given image details with active session user
	 * profile
	 * 
	 * @param id Image Id
	 * @return ResponseUserData
	 */
	@GetMapping("image/{id}")
	public ResponseUserData getImage(@PathVariable("id") String id) {
		LOGGER.info("getImage called for id {}", id);
		return imageService.getImage(id, validateUserCredential());
	}

	/**
	 * 
	 * This method defines the upload image functionality with the given user image
	 * details and active user profile
	 * 
	 * @param title       String
	 * @param type        String
	 * @param description String
	 * @param file        MaultiPartFile
	 * @return Image
	 * @throws Exception
	 */
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Image uploadFile(@RequestParam("title") String title, @RequestParam("type") String type,
			@RequestParam("description") String description, @RequestParam("image") MultipartFile file)
			throws Exception {
		// Process the file and form data
		return imageService.uploadImage(file, type, title, description, validateUserCredential());

	}

	/**
	 * Helps the authorization header from context and return the valid user profile
	 * 
	 * @return User
	 */
	private User validateUserCredential() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
	}
}