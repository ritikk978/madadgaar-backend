package com.madadgaar.foundation.domain;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "member_id")
    private long memberID;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "dob")
    private String dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "pin_code")
    private String pinCode;
    @Column(name = "pan_card")
    private String panCard;
    @Column(name = "aadhar_card")
    private String aadharCard;
    @Column(name = "aadhar_card_url")
    private String aadharCardUrl;
    @Column(name = "doc_url")
    private String docUrl;
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

}
