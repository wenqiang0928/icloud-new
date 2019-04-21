package com.honvay.hdms.dms.model.request;

import com.honvay.hdms.auth.core.AuthenticatedUser;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wenxinqiang
 * @date 2019/4/16 9:35
 */
@Data
public class UpdateCaseDescRequest {
    @NotNull
    private Integer id;

    private String caseDesc;

    private AuthenticatedUser user;
}
