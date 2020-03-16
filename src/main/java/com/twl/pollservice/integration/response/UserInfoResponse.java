package com.twl.pollservice.integration.response;

import com.twl.pollservice.model.enums.CpfStatus;
import lombok.Data;

@Data
public class UserInfoResponse {

    private CpfStatus status;
}
