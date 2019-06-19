package com.hsy.core.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsy.core.Interceptor.ContextInterceptor;
import com.hsy.core.constant.ConfigConstant;
import com.hsy.resource.constant.SystemConstant;

/**
 * @author 张梓枫
 * @Description
 * @date: 2019年1月17日 下午3:18:10
 */
@Configuration
public class WebContextConfig extends WebMvcConfigurationSupport {
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(contextInterceptor()).addPathPatterns(ConfigConstant.PATH).excludePathPatterns(
				SystemConstant.EXCLUDE_PATH);
        super.addInterceptors(registry);
    }

    @Bean
    public ContextInterceptor contextInterceptor() {
        ContextInterceptor securityInterceptor = new ContextInterceptor();
        return securityInterceptor;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
    
    /**
     * 自定义配置消息转换器，过滤json中null字段
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        MappingJackson2HttpMessageConverter jsonHmc = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8,
                MediaType.TEXT_HTML);
        jsonHmc.setSupportedMediaTypes(mediaTypes);

        ObjectMapper objectMapper = builder.build();
		objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        jsonHmc.setObjectMapper(objectMapper);
        converters.add(jsonHmc);
    }
}
