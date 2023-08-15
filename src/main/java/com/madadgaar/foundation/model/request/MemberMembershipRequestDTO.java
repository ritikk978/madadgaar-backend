package com.madadgaar.foundation.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberMembershipRequestDTO {
    Long memberID;
    String emailID;
    String dob;
    String gender;
    String address;
    String pinCode;
    String panCard;
    String aadharCard;

    String aadharCardUrl;
    String docUrl;
}
