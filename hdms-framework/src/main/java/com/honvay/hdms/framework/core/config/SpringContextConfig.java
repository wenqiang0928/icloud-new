package com.honvay.hdms.framework.core.config;

import com.honvay.hdms.framework.util.SpringContextUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class SpringContextConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        SpringContextUtils.setApplicationContext(contextRefreshedEvent.getApplicationContext());
    }
}
