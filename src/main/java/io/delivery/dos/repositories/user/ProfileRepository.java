package io.delivery.dos.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.menu.Menu;
import io.delivery.dos.models.user.response.ProfileResponse;


public interface ProfileRepository extends JpaRepository<ProfileResponse,String> {
	
	@Query(value="Select userid,username,token from profile where userid = :userid",nativeQuery = true)
	ProfileResponse findByUseridCustom(@Param("userid")String userid);
}