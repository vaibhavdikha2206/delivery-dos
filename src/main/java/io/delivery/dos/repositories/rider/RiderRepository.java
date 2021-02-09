package io.delivery.dos.repositories.rider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.rider.Riderdata;
import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.response.ProfileResponse;


public interface RiderRepository extends JpaRepository<ProfileResponse, Integer>{
	
	List<ProfileResponse> findByUseridNotIn(List<String> userids);
}
