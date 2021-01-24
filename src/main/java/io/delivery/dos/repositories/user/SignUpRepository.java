package io.delivery.dos.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.user.Profile;

public interface SignUpRepository extends JpaRepository<Profile, String> {

}
