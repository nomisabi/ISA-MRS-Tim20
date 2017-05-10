package com.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Friendship;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

	@Query("SELECT Object(f) FROM Friendship f WHERE f.guest.id = ?1 AND f.idFriend = ?2")
	public Friendship findByGuestAndFriendId(Long idGuest, Long idFriend);

	@Modifying
	@Transactional
	@Query("UPDATE Friendship f SET f.requestAccepted = ?3 WHERE f.guest.id = ?1 AND f.idFriend = ?2")
	int confirmFriendship(Long id, Long idfriend, boolean requestAccepted);

}
