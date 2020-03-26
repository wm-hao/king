package share.king.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import share.king.interceptor.HeaderInterceptor;
import share.king.interceptor.TokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HeaderInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/validate", "/user/exists");

    }
}
