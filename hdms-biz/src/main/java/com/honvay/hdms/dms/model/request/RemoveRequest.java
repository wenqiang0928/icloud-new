/***/
package com.honvay.hdms.dms.model.request;

import com.honvay.hdms.auth.core.AuthenticatedUser;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author LIQIU
 * created on 2019/3/1
 **/
@Data
public class RemoveRequest {

	@NotEmpty
	private List<Integer> documentIds;

	private AuthenticatedUser user;

}
