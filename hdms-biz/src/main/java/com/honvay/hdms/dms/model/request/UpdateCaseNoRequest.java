package com.honvay.hdms.dms.model.request;

import com.honvay.hdms.auth.core.AuthenticatedUser;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UpdateCaseNoRequest {
    @NotNull
    private Integer id;

    private String caseNo;

    private AuthenticatedUser user;
}
