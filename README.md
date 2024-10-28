# user-image-service
Service is used to do User Creation, Login and upload images to the given User Profile!

[Imgur API] is used to upload, read the images !

Postman collection is available to run all of the endpoints and explore the complete suite of endpoints related to,

 1. User Registration
 2. User Login - OAuth2.0 based JWT tokens will be available for the rest of the endpoints to access other functionalities
 3. Upload Image using Imgur API and attach the image into active user logon session using OAuth2.0 JWT tokens
 4. Get Image for the currect active user profile session using OAuth2.0 JWT tokens
 5. Get Images - list of all images and user profile details for the current active user logon session using OAuth2.0 JWT tokens

H2 Console endpoint is - http://localhost:8080/h2-console/ to allow the user to review the created user profiles and uploaded images against the user.

