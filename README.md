# user-image-service
Service is used to do User Creation, Login and upload images to the given User Profile!

[Imgur API](https://apidocs.imgur.com/) is used to upload, read the images !

Postman collection is available to run all of the endpoints and explore the complete suite of endpoints related to,

 1. User Registration
 2. User Login - OAuth2.0 based JWT tokens will be available for the rest of the endpoints to access other functionalities
 3. Upload Image using Imgur API and attach the image into active user logon session using OAuth2.0 JWT tokens
 4. Get Image for the currect active user profile session using OAuth2.0 JWT tokens
 5. Get Images - list of all images and user profile details for the current active user logon session using OAuth2.0 JWT tokens
 6. Delete Images - the given image will be deleted from Imgur and also from UserImage h2 table. i.e detaching the given image from active user profile

H2 Console endpoint is - http://localhost:8080/h2-console/ to allow the user to review the created user profiles and uploaded images against the user.

Following entities will be created and available in H2 database.
USERS

FIELD  	TYPE  	NULL  	KEY  	DEFAULT  
ID	INTEGER	NO	PRI	NULL
CREATED_AT	TIMESTAMP(6)	YES		NULL
UPDATED_AT	TIMESTAMP(6)	YES		NULL
EMAIL	CHARACTER VARYING(100)	NO	UNI	NULL
FULL_NAME	CHARACTER VARYING(255)	NO		NULL
PASSWORD	CHARACTER VARYING(255)	NO		NULL

USERIMAGE
FIELD  	TYPE  	NULL  	KEY  	DEFAULT  
ID	INTEGER	NO	PRI	NULL
USERID	INTEGER	YES		NULL
CREATED_AT	TIMESTAMP(6)	YES		NULL
UPDATED_AT	TIMESTAMP(6)	YES		NULL
DELETEHASH	CHARACTER VARYING(255)	YES		NULL
DESCRIPTION	CHARACTER VARYING(255)	YES		NULL
IMAGE_ID	CHARACTER VARYING(255)	YES		NULL
LINK	CHARACTER VARYING(255)	YES		NULL
TITLE	CHARACTER VARYING(255)	YES		NULL
TYPE	CHARACTER VARYING(255)	YES		NULL
