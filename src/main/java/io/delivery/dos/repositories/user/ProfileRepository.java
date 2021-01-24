package io.delivery.dos.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.user.response.ProfileResponse;


public interface ProfileRepository extends JpaRepository<ProfileResponse,String> {

	@Query(value="Select userid,username from profile where userid = :userid and password = :password",nativeQuery = true)
	ProfileResponse findUserProfile(@Param("userid")String userid,@Param("password")String password);

	@Query(value="Select userid,username from profile where userid = :userid",nativeQuery = true)
	ProfileResponse findByUserid(@Param("userid")String userid);
}