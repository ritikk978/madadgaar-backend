package com.madadgaar.foundation.repository;

import com.madadgaar.foundation.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRegisterRepository extends JpaRepository<Member, Long> {


    @Query(value = "select * from members mr where contact_number = :contactNumber", nativeQuery = true)
    Member findByContactNumber(@Param("contactNumber") String contactNumber);
    @Query(value = "select * from members mr where id = :memberID", nativeQuery = true)
    Member findByID(@Param("memberID") Long memberID);

}
