/***/
package com.honvay.hdms.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honvay.hdms.login.entity.LoginLog;
import com.honvay.hdms.login.model.LoginLogDto;
import com.honvay.hdms.login.model.LoginLogQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author wxq
 * created on 2019/3/13
 **/
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    /**
     * @param query
     * @return
     */
    List<LoginLogDto> searchLoginLog(@Param("query") LoginLogQuery query);

    /**
     * @param query
     * @return
     */
    Integer countLoginLog(@Param("query") LoginLogQuery query);

    /**
     * 获取登陆日志中最早和最近的登陆日期
     *
     * @return
     */
    @Select("select max(login_date) maxDate,min(login_date) minDate from hdms_login_log")
    Map<String, Object> getLoginDate();
}
