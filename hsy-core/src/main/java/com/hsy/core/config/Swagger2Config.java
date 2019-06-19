package com.hsy.core.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hsy.common.constant.CommonConstant;
import com.hsy.core.constant.ConfigConstant;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 张梓枫
 * @Description Swagger2配置
 * @date: 2019年1月4日 下午2:06:48
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	/**
	 * @author 张梓枫
	 * @Description: api
	 * @param @return
	 * @return Docket
	 * @throws Exception
	 */
	@Bean
	public Docket createMallApi() {
		return this.createDocket(ConfigConstant.API_PACKAGE, createMallInfo());
	}

	private ApiInfo createMallInfo() {
		return new ApiInfoBuilder().title(ConfigConstant.API_TITLE).termsOfServiceUrl("").contact(new Contact("shopbbc", "", "")).version(ConfigConstant.API_VERSIPN).build();
	}

	/**
	 * @author 张梓枫
	 * @Description:创建Swagger2容器
	 * @param @param  groupName 分组名称
	 * @param @param  basePacjage 扫描包路径
	 * @param @param  apiInfo 接口资源描述信息
	 * @param @return
	 * @return Docket
	 * @throws Exception
	 */
	private Docket createDocket(String basePackage, ApiInfo apiInfo) {
		List<Parameter> parameters = new ArrayList<Parameter>();
		this.createParameters(parameters, CommonConstant.SESSION_TOKEN, ConfigConstant.TOKEN_DESCRIPTION, false, "String");
		this.createParameters(parameters, CommonConstant.PAGE_NUM, CommonConstant.PAGE_NUM, false, "Integer");
		this.createParameters(parameters, CommonConstant.PAGE_SIZE, CommonConstant.PAGE_SIZE, false, "Integer");
		this.createParameters(parameters, CommonConstant.ORDER_BY, CommonConstant.ORDER_BY, false, "String");
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select().apis(RequestHandlerSelectors.basePackage(basePackage)).paths(PathSelectors.any()).build().globalOperationParameters(parameters);
	}

	private void createParameters(List<Parameter> parameters, String name, String desc, Boolean required, String modeRef) {
		ParameterBuilder builder = new ParameterBuilder();
		builder.name(name).description(desc).modelRef(new ModelRef(modeRef)).parameterType("header").required(required).build();
		parameters.add(builder.build());
	}
}
