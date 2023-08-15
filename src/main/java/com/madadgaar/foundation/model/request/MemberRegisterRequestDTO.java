package com.madadgaar.foundation.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegisterRequestDTO {
    String fullName;
    String contactNumber;
    String countryCode;
    String cityName;
    String stateName;
    String countryName;
}
