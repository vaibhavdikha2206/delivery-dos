package io.delivery.dos.repositories.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.response.ProfileResponse;

public interface ProfileRepositoryForAdmin extends JpaRepository<Profile,String> {
	
	@Query(value="Select userid,username,password,role,email from profile",nativeQuery = true)
	List<Profile> findProfileData();
}