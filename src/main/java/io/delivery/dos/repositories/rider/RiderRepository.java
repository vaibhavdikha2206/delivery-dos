package io.delivery.dos.repositories.rider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.rider.Riderdata;
import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.response.ProfileResponse;


public interface RiderRepository extends JpaRepository<ProfileResponse, Integer>{
	
	//used in finding free riders
	@Query(value="SELECT userid,username,token FROM profile WHERE userid Not IN (:userids) And role = :role",nativeQuery = true)
	List<ProfileResponse> findByUseridNotIn(@Param("userids")List<String> userids,@Param("role")String role);
	
	@Query(value="SELECT Count(Distinct(userid)) FROM profile WHERE role = :role",nativeQuery = true)
	Integer findCountByRole(@Param("role") String role);
	
	@Query(value="SELECT userid,username,token FROM profile WHERE role = :role",nativeQuery = true)
	List<ProfileResponse> findByRole(@Param("role")String role);

}
