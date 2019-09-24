package com.ucap.igsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 配置中心服务
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServer {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigServer.class);

	/**
	 * 启动方法
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServer.class, args);
		logger.info("配置中心服务启动成功！");
	}

}
