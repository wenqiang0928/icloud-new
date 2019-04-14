/***/
package com.honvay.hdms.dms.model.request;

import com.honvay.hdms.auth.core.AuthenticatedUser;
import lombok.Data;

/**
 * @author LIQIU
 * created on 2019/3/1
 **/
@Data
public class RenameRequest {

	private Integer id;
	private String name;
	private AuthenticatedUser user;

}
