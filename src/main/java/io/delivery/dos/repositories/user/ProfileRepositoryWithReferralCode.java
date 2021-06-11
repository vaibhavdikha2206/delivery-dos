package io.delivery.dos.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.user.ProfileWithReferralCode;

public interface ProfileRepositoryWithReferralCode extends JpaRepository<ProfileWithReferralCode,String> {
	
	@Query(value="Select userid,username,referralcode,credits,token from profile where referralcode = :referralcode",nativeQuery = true)
	ProfileWithReferralCode findCountByReferralcodeCustom(@Param("referralcode")String referralcode);
	
	@Query(value="Select userid,username,referralcode,credits,token from profile where userid = :userid",nativeQuery = true)
	ProfileWithReferralCode findByUseridCustom(@Param("userid")String userid);
	
	
	@Modifying
	@Query(value="Update profile set credits = (credits + 25) where userid = :userid",nativeQuery = true)
	Integer updateProfileCredits(@Param("userid")String userid);
	
	@Modifying
	@Query(value="Update profile set credits = (credits - :usedcredits) where userid = :userid and credits > 0",nativeQuery = true)
	Integer updateProfileWithGivenCredits(@Param("userid")String userid,@Param("usedcredits")int usedcredits);

}