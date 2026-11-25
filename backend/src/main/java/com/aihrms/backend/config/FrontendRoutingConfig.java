
package com.aihrms.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FrontendRoutingConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("forward:/index.html");
        registry.addViewController("/dashboard").setViewName("forward:/index.html");
        registry.addViewController("/employees").setViewName("forward:/index.html");
        registry.addViewController("/departments").setViewName("forward:/index.html");
        registry.addViewController("/attendance").setViewName("forward:/index.html");
        registry.addViewController("/leaves").setViewName("forward:/index.html");
        registry.addViewController("/payroll").setViewName("forward:/index.html");
        registry.addViewController("/recruitment").setViewName("forward:/index.html");
        registry.addViewController("/candidates").setViewName("forward:/index.html");
        registry.addViewController("/notifications").setViewName("forward:/index.html");
        registry.addViewController("/settings").setViewName("forward:/index.html");
    }
}
