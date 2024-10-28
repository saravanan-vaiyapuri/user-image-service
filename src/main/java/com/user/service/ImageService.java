package com.user.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.user.model.Data;
import com.user.model.Image;
import com.user.model.ResponseData;
import com.user.model.ResponseUserData;
import com.user.model.User;
import com.user.model.UserImage;
import com.user.repository.UserImageRepository;

/**
 * This provides an ability to interact with User Image upload/view using Imgur
 * API integrations
 */
@Service
public class ImageService {

	private static final String IMGUR_CLIENT_ID = "b3c655b45780859";

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

	private UserImageRepository userImageRepository = null;

	public ImageService(UserImageRepository userImageRepository) {
		this.userImageRepository = userImageRepository;
	}

	/**
	 * 
	 * This method is used to read Image details from H2 database for the given
	 * image id and active session user profile
	 * 
	 * @param id          String
	 * @param currentUser User
	 * @return ResponseUserData
	 */
	public ResponseUserData getImage(String id, User currentUser) {
		LOGGER.info("getImage called for id {}", id);

		List<UserImage> findAll = userImageRepository.findAll();
		Iterator<UserImage> itr = findAll.iterator();
		Image returnImage = null;
		while (itr.hasNext()) {
			UserImage image2 = (UserImage) itr.next();
			// validate the image attached to the given user profile with the given image id
			// ONLY
			if (image2.user.getId().equals(currentUser.getId()) && image2.imageId.equals(id)) {
				returnImage = new Image();
				returnImage.id = image2.imageId;
				returnImage.title = image2.title;
				returnImage.link = image2.link;
				returnImage.type = image2.type;
				returnImage.description = image2.description;
			}
		}

		ResponseUserData data = new ResponseUserData();
		data.status = "200";
		data.success = "true";
		data.user = currentUser;
		ArrayList<Image> images = new ArrayList<>();
		images.add(returnImage);
		data.images = images;
		return data;
	}

	/**
	 * Returns all the image details from H2 database for the given active user
	 * profile
	 * 
	 * @param currentUser User
	 * @return ResponseUserData
	 */
	public ResponseUserData getImages(User currentUser) {
		// use UserImageRepository to find/list all the user profile with their images
		List<UserImage> findAll = userImageRepository.findAll();

		Iterator<UserImage> itr = findAll.iterator();
		Image returnImage = null;
		ArrayList<Image> images = new ArrayList<>();
		while (itr.hasNext()) {
			UserImage image2 = (UserImage) itr.next();
			// validate the images attached to the given user profile
			if (image2.user.getId().equals(currentUser.getId())) {
				returnImage = new Image();
				returnImage.id = image2.imageId;
				returnImage.title = image2.title;
				returnImage.link = image2.link;
				returnImage.type = image2.type;
				returnImage.description = image2.description;
				// populated all the valid image details for the given user into a List
				images.add(returnImage);
			}
		}

		ResponseUserData data = new ResponseUserData();
		data.status = "200";
		data.success = "true";
		data.user = currentUser;
		data.images = images;
		return data;
	}

	/**
	 * 
	 * This method is used to upload image using Imgur API and attach the image
	 * details with the given active session user profile
	 * 
	 * @param urlImage    MultipartFile
	 * @param type        String
	 * @param title       String
	 * @param description String
	 * @param user        User
	 * @return Image
	 * @throws Exception
	 */
	public Image uploadImage(MultipartFile urlImage, String type, String title, String description, User user)
			throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		// Create the form data
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("type", type);
		body.add("title", title);
		body.add("description", description);
		body.add("image", new FileSystemResource(convert(urlImage)));

		// Set the headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
		// Create the request entity and initiate Imgur API image Upload process
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<ResponseData> response = restTemplate.exchange("https://api.imgur.com/3/image/", HttpMethod.POST,
				requestEntity, ResponseData.class);

		// read the uploaded image details to return as part of Upload API response
		Image image = new Image();
		Data res = response.getBody().data;
		image.id = res.id;
		image.title = res.title;
		image.link = res.link;
		image.type = res.type;
		image.description = res.description;

		// populate the UserImage to persist in H2 database
		UserImage userImage = new UserImage(user);
		userImage.link = image.link;
		userImage.title = res.title;
		userImage.link = res.link;
		userImage.type = res.type;
		userImage.imageId = image.id;
		userImage.description = res.description;

		// save or attach the image details with given active session user profile
		userImageRepository.save(userImage);

		return image;

	}

	/**
	 * this helps in creating File object from the given MultipartFile object
	 * 
	 * @param file MultipartFile
	 * @return File
	 * @throws java.io.IOException
	 */
	public File convert(MultipartFile file) throws java.io.IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}