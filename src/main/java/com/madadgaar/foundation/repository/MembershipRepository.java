package com.madadgaar.foundation.repository;

import com.madadgaar.foundation.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MembershipRepository extends JpaRepository<Membership, Long> {


    @Query(value = "select * from membership m where member_id = :memberID", nativeQuery = true)
    Membership findByMemberID(@Param("memberID") long memberID);
    @Query(value = "select * from membership m where id = :membershipID", nativeQuery = true)
    Membership findByMembershipID(@Param("membershipID") long memberID);

}
