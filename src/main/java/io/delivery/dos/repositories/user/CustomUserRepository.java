package io.delivery.dos.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import io.delivery.dos.models.user.CustomUserdata;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomUserRepository extends JpaRepository<CustomUserdata, Integer> {

	
	@Query(value="Select surname from Userdata where name = :firstName",nativeQuery = true)
	String findNameTest(@Param("firstName")String firstName);
}