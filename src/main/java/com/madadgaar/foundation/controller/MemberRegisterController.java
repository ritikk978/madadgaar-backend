package com.madadgaar.foundation.controller;

import com.madadgaar.foundation.domain.Member;
import com.madadgaar.foundation.domain.Membership;
import com.madadgaar.foundation.model.request.MemberMembershipRequestDTO;
import com.madadgaar.foundation.model.request.MemberRegisterRequestDTO;
import com.madadgaar.foundation.repository.MemberRegisterRepository;
import com.madadgaar.foundation.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MemberRegisterController {

    @NotNull private final MemberRegisterRepository memberRegisterRepository;
    @NotNull private final MembershipRepository membershipRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody MemberRegisterRequestDTO memberRegisterRequestDTO) {
        try {
            if(memberRegisterRequestDTO != null){
                Member memberRegisterTable = memberRegisterRepository.findByContactNumber(memberRegisterRequestDTO.getContactNumber());
                if(memberRegisterTable != null){
                    return ResponseEntity.ok(memberRegisterTable);
                }else{
                    memberRegisterTable = new Member();
                    memberRegisterTable.setCityName(memberRegisterRequestDTO.getCityName());
                    memberRegisterTable.setFullName(memberRegisterRequestDTO.getFullName());
                    memberRegisterTable.setContactNumber(memberRegisterRequestDTO.getContactNumber());
                    memberRegisterTable.setStateName(memberRegisterRequestDTO.getStateName());
                    memberRegisterTable.setCountyName(memberRegisterRequestDTO.getCountryName());
                    memberRegisterTable.setCountryCode(memberRegisterRequestDTO.getCountryCode());
                    memberRegisterTable.setMembershipStatus(false);
                    memberRegisterTable.setCreated_at(ZonedDateTime.now());
                    memberRegisterTable.setModifiedAt(ZonedDateTime.now());
                    ;
                    return ResponseEntity.ok(memberRegisterRepository.save(memberRegisterTable));
                }
            }
            return null;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/member/{memberID}")
    public ResponseEntity<?> getMemberData(@PathVariable("memberID") Long memberID){
        try{
            Member member = memberRegisterRepository.findByID(memberID);
            if(member == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(member);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/membership/{memberID}")
    public ResponseEntity<?> getMembership(@PathVariable("memberID") Long memberID){
        try{
            Membership membership = membershipRepository.findByMembershipID(memberID);
            if(membership == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(membership);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/membership")
    public ResponseEntity<?> saveMembershipDetails(@RequestBody MemberMembershipRequestDTO data) {
        try {
            if(data != null){
                Member member = memberRegisterRepository.findByID(data.getMemberID());
                if(member != null){
                    Membership membership = membershipRepository.findByMemberID(data.getMemberID());
                    if(membership != null){
                        return ResponseEntity.ok(membership);
                    }
                    membership = new Membership();
                    membership.setMemberID(data.getMemberID());
                    membership.setDob(data.getDob());
                    membership.setAddress(data.getAddress());
                    membership.setAadharCard(data.getAadharCard());
                    membership.setEmailId(data.getEmailID());
                    membership.setGender(data.getGender());
                    membership.setPanCard(data.getPanCard());
                    membership.setPinCode(data.getPinCode());
                    membership.setDocUrl(data.getDocUrl());
                    membership.setCreatedAt(ZonedDateTime.now());
                    membership.setModifiedAt(ZonedDateTime.now());
                    membership.setAadharCardUrl(data.getAadharCardUrl());
                    member.setMembershipStatus(true);
                    memberRegisterRepository.save(member);
                    return ResponseEntity.ok(membershipRepository.save(membership));
                }else{
                    return ResponseEntity.noContent().build();
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

}
