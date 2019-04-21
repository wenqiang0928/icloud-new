package com.honvay.hdms.auth.web.deadline.filter;

import com.honvay.hdms.framework.util.SpringContextUtils;
import com.honvay.hdms.login.mapper.LoginLogMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
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
import java.util.HashMap;
import java.util.Map;

public class DeadlineFilter extends GenericFilterBean {

    private Map<RequestMatcher, AuthenticationFailureHandler> requestMatcherMap = new HashMap<>();

    public void addRequestMatcher(RequestMatcher requestMatcher, AuthenticationFailureHandler handler) {
        this.requestMatcherMap.put(requestMatcher, handler);
    }

    private AuthenticationFailureHandler requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        for (RequestMatcher matcher : requestMatcherMap.keySet()) {
            if (matcher.matches(request)) {
                return requestMatcherMap.get(matcher);
            }
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        AuthenticationFailureHandler authenticationFailureHandler = this.requiresAuthentication(request, response);
        if (authenticationFailureHandler == null) {
            filterChain.doFilter(request, response);
            return;
        }

        LoginLogMapper loginLogMapper = SpringContextUtils.getBean(LoginLogMapper.class);
        long tryoutDay = Long.parseLong(SpringContextUtils.getBean(Environment.class).getProperty("hdms.system.tryoutDay"));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentDate = sdf.parse(sdf.format(new Date()));
            Map<String, Object> result = loginLogMapper.getLoginDate();
            if (result == null) {
                filterChain.doFilter(request, response);
                return;
            }
            Date maxDate = sdf.parse(result.get("maxDate").toString());
            Date minDate = sdf.parse(result.get("minDate").toString());
            if (currentDate.getTime() < maxDate.getTime()) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new InsufficientAuthenticationException("试用期已失效！"));
                return;
            }
            if (currentDate.getTime() - minDate.getTime() > tryoutDay * 24 * 60 * 60 * 1000) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new InsufficientAuthenticationException("试用期已失效！"));
                return;
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
