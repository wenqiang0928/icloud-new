package com.honvay.hdms.auth.web.deadline.filter;

import com.honvay.hdms.auth.web.handler.WebAuthenticationFailureHandler;
import com.honvay.hdms.login.mapper.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class DeadlineFilter extends GenericFilterBean {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private WebAuthenticationFailureHandler webAuthenticationFailureHandler;

    @Value("${hdms.system.tryoutDay}")
    private int tryoutDay;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentDate = sdf.parse(sdf.format(new Date()));
            Map<String, Object> result = this.loginLogMapper.getLoginDate();
            if (result == null) {
                filterChain.doFilter(request, response);
                return;
            }
            Date maxDate = sdf.parse(result.get("maxDate").toString());
            Date minDate = sdf.parse(result.get("minDate").toString());
            if (currentDate.getTime() < maxDate.getTime()) {
                this.webAuthenticationFailureHandler.onAuthenticationFailure(request, response, new InsufficientAuthenticationException("试用期已失效！"));
                return;
            }
            if (currentDate.getTime() - minDate.getTime() > tryoutDay * 24 * 60 * 60 * 1000) {
                this.webAuthenticationFailureHandler.onAuthenticationFailure(request, response, new InsufficientAuthenticationException("试用期已失效！"));
                return;
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
