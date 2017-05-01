package com.example.domain.DTOs;

public class FriendRequest {
	private Long idGuest;
	private String emailFriend;

	public FriendRequest() {
	}

	public Long getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(Long idGuest) {
		this.idGuest = idGuest;
	}

	public String getEmailFriend() {
		return emailFriend;
	}

	public void setEmailFriend(String emailFriend) {
		this.emailFriend = emailFriend;
	}

	@Override
	public String toString() {
		return "FriendRequest [idGuest=" + idGuest + ", emailFriend=" + emailFriend + "]";
	}

}
