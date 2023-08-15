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
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "state_name")
    private String stateName;
    @Column(name = "country_name")
    private String countyName;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "membership_status")
    private boolean membershipStatus;
    @Column(name = "created_at")
    private ZonedDateTime created_at;
    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

}
