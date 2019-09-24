package com.ucap.igsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 网关服务
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulServer {

	private static Logger logger = LoggerFactory.getLogger(ZuulServer.class);

	/** 启动方法 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(ZuulServer.class).web(true).run(args);
		logger.info("网关服务启动成功！");
	}

	/**
	 * 允许跨域请求
	 * @return attention:简单跨域就是GET，HEAD和POST请求，但是POST请求的"Content-Type"只能是application/x-www-form-urlencoded, multipart/form-data 或 text/plain 反之，就是非简单跨域，此跨域有一个预检机制，说直白点，就是会发两次请求，一次OPTIONS请求，一次真正的请求
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 允许cookies跨域
		config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
		config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
		config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
		config.addAllowedMethod("GET");// 允许GET的请求方法
		config.addAllowedMethod("POST");// 允许POST的请求方法
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
