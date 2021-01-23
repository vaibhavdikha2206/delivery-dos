package io.delivery.dos.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.security.models.ProfileUser;



public interface UserRepository extends JpaRepository<ProfileUser, String> {

	Optional<ProfileUser> findByUserid(String userid);
}
