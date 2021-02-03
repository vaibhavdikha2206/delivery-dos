package io.delivery.dos.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.user.Profile;

public interface UpdateTokenRepository extends JpaRepository<Profile, Integer> {

	@Modifying
	@Query(value="Update profile set token = :token where userid = :userid",nativeQuery = true)
	Integer updateToken(@Param("userid")String userid,@Param("token")String token);
	
}
