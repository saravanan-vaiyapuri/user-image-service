
package com.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "userimage")
@Entity
@Setter
@Getter
public class UserImage implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Integer id;

	@JsonProperty("link")
	public String link;
	@JsonProperty("type")
	public String type;
	@JsonProperty("title")
	public String title;
	@JsonProperty("imageId")
	public String imageId;
	@JsonProperty("description")
	public String description;
	@JsonProperty("deletehash")
	public String deletehash;
	@ManyToOne
	@JoinColumn(name = "userid")
	public User user;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public UserImage setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public UserImage setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	@Override
	public String toString() {
		return "UserImage {" + "id=" + id + ", user=" + user.getEmail() +", imageId= "+ imageId+", link= "+link+", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ '}';
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	public UserImage() {

	}
	public UserImage(User user) {

	this.user=user;
	}
}
