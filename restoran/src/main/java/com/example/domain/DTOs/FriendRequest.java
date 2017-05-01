package com.example.domain.DTOs;

public class FriendRequest {
	private Long idGuest;
	private Long idFriend;

	public FriendRequest() {
	}

	public Long getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(Long idGuest) {
		this.idGuest = idGuest;
	}

	public Long getIdFriend() {
		return idFriend;
	}

	public void setIdFriend(Long idFriend) {
		this.idFriend = idFriend;
	}

	@Override
	public String toString() {
		return "FriendRequest [idGuest=" + idGuest + ", idFriend=" + idFriend + "]";
	}

}
