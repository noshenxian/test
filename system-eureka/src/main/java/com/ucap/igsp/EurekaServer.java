package com.ucap.igsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    private static Logger logger = LoggerFactory.getLogger(EurekaServer.class);

	/** 启动方法 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaServer.class).web(true).run(args);
		logger.info("注册中心启动成功！");
	}

}
